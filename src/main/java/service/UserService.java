package service;

import com.mysql.cj.jdbc.CallableStatement;
import model.User;
import utils.MySQLConnUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {

    public static  String SELECT_ALL_USERS = " " +
            "SELECT " +
            "u.userId, " +
            "u.full_name, " +
            "u.userName, " +
            "u.phone, " +
            "u.age, " +
            "u.address, " +
            "u.email," +
            "u.role,  " +
            "u.createdAT, " +
            "u.updatedAT, " +
            "u.img " +
            "FROM users AS u;";


//    public static final String SP_INSERT_USER = "{CALL SP_INSERT_USER (?,?,?,?,?,?,?)}"

    public static  String INSERT_USER = "" +
            "INSERT INTO milk_tea_hana.users (" +
            "`userName`, " +
            "`password`, " +
            "`full_name`, " +
            "`phone`, " +
            "`age`, " +
            "`email`, " +
            "`address`, " +
            "`role`, " +
            "`createdAT`, " +
            "`img` ) " +
            "VALUES (?,?,?,?,?,?,?,?,now(),?);";

    public static String SELECT_USER_BY_ID = "SELECT u.full_name, u.age, u.address, u.updatedAT, u.img FROM users AS u WHERE u.userId = ?;";

    public static  String UPDATE_USER = "" +
            "UPDATE users AS u " +
            "SET " +
                " u.full_name = ?, " +
                " u.age = ?, " +
                " u.address = ?, " +
                " u.updatedAT = now(), " +
                " u.img = ? " +
            "WHERE u.userId = ?;";
//            "UPDATE `milk_tea_hana`.`users` SET `full_name`= ?, `age`= ?, `address`= ?, `img`= ?, updatedAT = now() WHERE userId = ?;";

    public static  String USER_EXIST_BY_EMAIL = "" +
            "SELECT COUNT(*) AS COUNT " +
            "FROM users AS u " +
            "WHERE u.email = ?;";

    public static String SEARCH_BY_KEY = "" +
            "SELECT * " +
            "FROM users " +
            "WHERE userName LIKE ? OR full_name LIKE ? OR phone LIKE ? OR age LIKE ? OR email LIKE ? OR address LIKE ? OR role LIKE ?;";

    @Override
    public List<User> searchByKey(String key) {
        List<User> userList = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(SEARCH_BY_KEY);
            statement.setString(1, '%' + key + '%');
            statement.setString(2, '%' + key + '%');
            statement.setString(3, '%' + key + '%');
            statement.setString(4, '%' + key + '%');
            statement.setString(5, '%' + key + '%');
            statement.setString(6, '%' + key + '%');
            statement.setString(7, '%' + key + '%');
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userId");
                String userName = rs.getString("userName");
               String password = rs.getString("password");
                String fullName = rs.getString("full_name");
                String phone = rs.getString("phone");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String role = rs.getString("role");
                String createdAT = String.valueOf(rs.getDate("createdAT"));
                String updatedAT = String.valueOf(rs.getDate("updatedAT"));
                String img = rs.getString("img");

                userList.add(new User(userId, userName, password, fullName, phone, age, email, address, role, createdAT, updatedAT, img));
            }
        }
        catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return userList;
    }

    @Override
    public boolean create(User user) {
        boolean success =  false;

        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(INSERT_USER);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setString(4, user.getPhone());
            statement.setInt(5, user.getAge());
            statement.setString(6, user.getEmail());
            statement.setString(7, user.getAddress());
            statement.setString(8, user.getRole());
            statement.setString(9, user.getImg());
            statement.execute();

            success = true;


        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return success;
    }

    @Override
    public boolean update(User user) {
        boolean success = false;

        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(UPDATE_USER);
            statement.setString(1, user.getFullName());
            statement.setInt(2, user.getAge());
            statement.setString(3, user.getAddress());
            statement.setString(4, user.getImg());
            statement.setInt(5, user.getUserId());
            System.out.println(statement);
            statement.executeUpdate();

            success = true;

        }
        catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return success;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }


    @Override
    public User findById(int userId) {
        User user = null;

        try {
            Connection connection = MySQLConnUtils.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareCall(SELECT_USER_BY_ID);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String fullname = rs.getString("full_name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                String update = String.valueOf(rs.getDate("updatedAT"));
                String img = rs.getString("img");

                user = new User( fullname , age , address,update, img);
            }
        }
        catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return user;
    }

    @Override
    public boolean existsByEmail(String email) {
        boolean exist = false;

        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(USER_EXIST_BY_EMAIL);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    exist = true;
                }
            }
        }
        catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return exist;
    }


    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();

        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(SELECT_ALL_USERS);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int usedId = rs.getInt("userId");
                String userName = rs.getString("userName");
                String fullName = rs.getString("full_name");
                String phone = rs.getString("phone");
                int age  = rs.getInt("age");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String role = rs.getString("role");
                String createdAT = String.valueOf(rs.getDate("createdAT"));
                String updatedAT = String.valueOf(rs.getDate("updatedAT"));
                String img = rs.getString("img");

                userList.add(new User(usedId,userName,fullName,phone,age,email,address,role,createdAT,updatedAT,img));
            }
        }
        catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return userList;
    }
}

