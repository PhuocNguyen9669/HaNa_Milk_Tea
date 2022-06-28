package controller;

import model.Product;
import service.ProductService;
import utils.MySQLConnUtils;
import utils.ValidateUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static service.ProductService.SEARCH_BY_KEY;

@WebServlet(name = "CPProductServlet", urlPatterns = "/cp/product")
public class CPProductServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                showCreatePage(req, resp);
                break;
            case "edit":
                showEditPage(req, resp);
                break;
            case "list":
                showListPage(req, resp);
                break;
            case "remove":
                doRemove(req, resp);
                break;
//            case "search":
//                dosearchByKey(req, resp);
//                break;
            default:
                dosearchByKey(req, resp);
                break;
        }
    }

    public void dosearchByKey(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        List<Product> productList = null;

        String key = "";
        if (req.getParameter("search") != null) {
            key = req.getParameter("search");
            productList = productService.searchByKey(key);

        } else {
            productList = productService.findAll();
        }
        req.setAttribute("productList", productList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/product/list.jsp");
        dispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                doCreate(req, resp);
                break;
            case "edit":
                doUpdate(req, resp);
                break;
            case "remove":
                doRemove(req, resp);
                break;
            case "list":
                showListPage(req, resp);
                break;
            case "search":
                dosearchByKey(req, resp);
                break;
            default:
                showListPage(req, resp);
                break;
        }
    }

    public void doCreate(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        Product product;
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/product/create.jsp");
        String nameProduct = req.getParameter("nameProduct").trim();
        String priceProduct = req.getParameter("priceProduct").trim();
        String quantityProduct = req.getParameter("quantityProduct").trim();
        String typeProduct = req.getParameter("typeProduct").trim();
        String description = req.getParameter("description").trim();
        String createdAT = req.getParameter("createdAT").trim();
        String image = req.getParameter("image").trim();


        List<String> errors = new ArrayList<>();

        boolean isPrice = ValidateUtils.isPriceValid(priceProduct);
        boolean isQuantity = ValidateUtils.isQuantityValid(quantityProduct);

        product = new Product(nameProduct, priceProduct, quantityProduct, typeProduct, description, image);

        if (nameProduct.isEmpty() ||
                priceProduct.isEmpty() ||
                quantityProduct.isEmpty() ||
                typeProduct.isEmpty() ||
                description.isEmpty() ||
                image.isEmpty()) {
            errors.add("Vui lòng nhâp đày đủ thông tin!");
        } else if (nameProduct.isEmpty()) {
            errors.add("");
        }
        if (nameProduct.isEmpty()) {
            errors.add("NameProduct không được để trống!");
        }
        if (priceProduct.isEmpty()) {
            errors.add("priceProduct không được để trống!");
        }
        if (!isPrice) {
            errors.add("Price không đúng định dạng!");
        }
        if (quantityProduct.isEmpty()) {
            errors.add("quantityProduct không được để trống!");
        }
        if (!isQuantity) {
            errors.add("Quantity không đúng định dạng!");
        }
        if (typeProduct.isEmpty()) {
            errors.add("typeProduct không được để trống!");
        }
        if (description.isEmpty()) {
            errors.add("description không được để trống!");
        }
        if (image.isEmpty()) {
            errors.add("url không được để trống!");
        }
        if (errors.size() == 0) {
            product = new Product(nameProduct, priceProduct, quantityProduct, typeProduct, description, image);

            boolean success = false;

            success = productService.create(product);

            if (success) {
                req.setAttribute("success", true);
            } else {
                req.setAttribute("errors", product);
                req.setAttribute("productCreate", product);
            }
            dispatcher.forward(req, resp);
        }
    }

    public void doRemove(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("id"));
        productService.remove(productId);
        List<Product> productList = productService.findAll();
        req.setAttribute("productList", productList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/product/list.jsp");
        dispatcher.forward(req, resp);
    }

    public void doUpdate(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        Product updateProduct;

        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/product/edit.jsp");
        int productId = (Integer.parseInt(req.getParameter("id").trim()));
        String nameProduct = req.getParameter("nameProduct").trim();
        String priceProduct = req.getParameter("priceProduct").trim();
        String quantityProduct = String.valueOf(Integer.parseInt(req.getParameter("quantityProduct").trim()));
        String typeProduct = req.getParameter("typeProduct").trim();
        String description = req.getParameter("description").trim();
        String image = req.getParameter("image").trim();

        List<String> errors = new ArrayList<>();

        updateProduct = new Product(productId, nameProduct, priceProduct, quantityProduct, typeProduct, description, image);

        if (nameProduct.isEmpty() ||
                priceProduct.isEmpty() ||
                quantityProduct.isEmpty() ||
                typeProduct.isEmpty() ||
                description.isEmpty() ||
                image.isEmpty()) {
            errors.add("Vui lòng nhập đủ thông tin!");
        }
        if (nameProduct.isEmpty()) {
            errors.add("NameProduct không được để trống!");
        }
        if (priceProduct.isEmpty()) {
            errors.add("priceProduct không được để trống!");
        }
        if (quantityProduct.isEmpty()) {
            errors.add("QuantityProduct không được để trống!");
        }
        if (typeProduct.isEmpty()) {
            errors.add("TypeProduct không được để trống!");
        }
        if (description.isEmpty()) {
            errors.add("Description không được để trống!");
        } else if (errors.size() == 0) {
            updateProduct = new Product(productId, nameProduct, priceProduct, quantityProduct, typeProduct, description, image);
            boolean success = false;
            success = productService.update(updateProduct);
            if (success) {
                req.setAttribute("success", true);
            } else {
                req.setAttribute("errors", true);
                errors.add("Dữ liệu không đúng, vui lòng kiểm tra lại!");
            }
        }
        if (errors.size() > 0) {
            req.setAttribute("errors", errors);
            req.setAttribute("product", updateProduct);
        }
        dispatcher.forward(req, resp);

    }

    public void showCreatePage(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/product/create.jsp");
        List<Product> productList = productService.findAll();
        req.setAttribute("productList", productList);
        dispatcher.forward(req, resp);
    }

    public void showListPage(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/product/list.jsp");
        List<Product> productList = productService.findAll();
        req.setAttribute("productList", productList);
        dispatcher.forward(req, resp);
    }

    public void showEditPage(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(productId);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/product/edit.jsp");
        req.setAttribute("product", product);
        dispatcher.forward(req, resp);
    }


}
