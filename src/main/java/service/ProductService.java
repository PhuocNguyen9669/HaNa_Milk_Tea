package service;


import model.Product;
import model.User;
import utils.FormatVND;
import utils.MySQLConnUtils;

import javax.servlet.RequestDispatcher;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {

    private static String SELECT_ALL_PRODUCTS = "" +
            "SELECT " +
            "p.productId, " +
            "p.nameProduct, " +
            "p.priceProduct, " +
            "p.quantityProduct, " +
            "p.typeProduct, " +
            "p.description, " +
            "p.createdAT, " +
            "p.updatedAT, " +
            "p.image " +
            "FROM products AS p";

    private static String SELECT_PRODUCT_BY_ID = "" +
            "SELECT COUNT(*) AS count " +
            "FROM products AS p " +
            "WHERE p.productId = ?;";

    private static String INSER_PRODUCT = "" +
            "INSERT INTO milk_tea_hana.products (" +
            "nameProduct, " +
            "priceProduct, " +
            "quantityProduct, " +
            "typeProduct, " +
            "description, " +
            "createdAT," +
            "updatedAT,  " +
            "image )" +
            "VALUES (?,?,?,?,?,now(),null,?);";

    private static String UPDATE_PRODUCT = "" +
            "UPDATE products AS p " +
            "SET " +
                "p.nameProduct = ?, " +
                "p.priceProduct = ?, " +
                "p.quantityProduct = ?, " +
                "p.typeProduct = ?, " +
                "p.description = ?, " +
                "p.updatedAT = now(), " +
                "p.image = ? " +
            "WHERE p.productId = ?;";

    public static String REMOVE_PRODUCT_BY_ID = "" +
            "DELETE FROM products AS p " +
            "WHERE p.productId = ?;";

    public static String SEARCH_BY_KEY = "" +
            "SELECT * " +
            "FROM `products` " +
            "WHERE nameProduct LIKE ? OR priceProduct LIKE ? OR quantityProduct LIKE ? OR TypeProduct LIKE ? OR Description LIKE ?";


    @Override
    public List<Product> searchByKey(String key) {
        List<Product> productList = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();
            CallableStatement statement = connection.prepareCall(SEARCH_BY_KEY);
            statement.setString(1, '%' + key + '%');
            statement.setString(2, '%' + key + '%');
            statement.setString(3, '%' + key + '%');
            statement.setString(4, '%' + key + '%');
            statement.setString(5, '%' + key + '%');
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int productId = rs.getInt("productId");
                String nameProduct = rs.getString("nameProduct");
                String priceProduct = String.valueOf(FormatVND.doubleToVND(rs.getDouble("priceProduct")));
                String quantityProduct = String.valueOf(rs.getInt("quantityProduct"));
                String typeProduct = rs.getString("typeProduct");
                String description = rs.getString("description");
                String createdAT = String.valueOf(rs.getDate("createdAT"));
                String updatedAT = String.valueOf(rs.getDate("updatedAT"));
                String image = rs.getString("image");

                productList.add(new Product(productId, nameProduct, priceProduct, Integer.parseInt(quantityProduct), typeProduct, description, createdAT, updatedAT, image));
            }
        }
        catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return productList;

    }

    @Override
    public boolean create(Product product) {
        boolean success = false;

        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSER_PRODUCT);
            statement.setString(1, product.getNameProduct());
            statement.setDouble(2, product.getPriceProduct());
            statement.setInt(3, product.getQuantityProduct());
            statement.setString(4, product.getTypeProduct());
            statement.setString(5, product.getDescription());
            statement.setString(6, product.getImage());
            statement.execute();

            success =true;

        }
        catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return success;
    }

    @Override
    public boolean update(Product product) {
        boolean success = false;

        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(UPDATE_PRODUCT);
            statement.setString(1, product.getNameProduct());
            statement.setDouble(2, product.getPriceProduct());
            statement.setInt(3, product.getQuantityProduct());
            statement.setString(4, product.getTypeProduct());
            statement.setString(5, product.getDescription());
            statement.setString(6, product.getImage());
            statement.setInt(7, product.getProductId());
            System.out.println(statement);
            statement.executeUpdate();

            success = true;

        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return success;
    }

    @Override
    public boolean remove(int id) {
        boolean success = false;

        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(REMOVE_PRODUCT_BY_ID);
            statement.setInt(1, id);


            success = statement.executeUpdate() > 0;

        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return success;
    }

    @Override
    public List<Product> findAll() {
       List<Product> productList = new ArrayList<>();

        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(SELECT_ALL_PRODUCTS);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("productId");
                String nameProduct = rs.getString("nameProduct");
                String priceProduct = String.valueOf(FormatVND.doubleToVND(rs.getDouble("priceProduct")));
                String quantityProduct = String.valueOf(rs.getInt("quantityProduct"));
                String typeProduct = rs.getString("typeProduct");
                String description = rs.getString("description");
                String createdAT = String.valueOf(rs.getDate("createdAT"));
                String updatedAT = String.valueOf(rs.getDate("updatedAT"));
                String image = rs.getString("image");


                productList.add(new Product(productId, nameProduct, Double.parseDouble(priceProduct), Integer.parseInt(quantityProduct), typeProduct, description,createdAT, updatedAT, image));
            }
        }
        catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return productList;
    }

    @Override
    public Product findById(int productId) {
        Product product = null;

        try {
            Connection connection = MySQLConnUtils.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareCall(SELECT_PRODUCT_BY_ID);
            statement.setInt(1, productId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nameProduct = rs.getString("nameProduct");
                String piceProduct = String.valueOf(rs.getDouble("piceProduct"));
                int quantityProduct = rs.getInt("quantityProduct");
                String typeProduct = rs.getString("typeProduct");
                String description = rs.getString("description");
                String image = rs.getString("image");

                product = new Product(productId, nameProduct, piceProduct, quantityProduct, typeProduct, description, image);
            }
        }
        catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return product;
    }

}
