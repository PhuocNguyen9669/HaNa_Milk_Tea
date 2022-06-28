package controller;

import model.User;
import service.IUserService;
import service.UserService;
import utils.ValidateUtils;

import javax.management.relation.Role;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CPUserServlet", urlPatterns = "/cp/user")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class CPUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IUserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
//            case "search":
//                doSearchByKey(req, resp);
//                break;
            default:
                doSearchByKey(req, resp);
                break;
        }
    }

    public void doSearchByKey (HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        List<User> userList = null;

        String key = "";
        if (req.getParameter("search") != null){
            key = req.getParameter("search");
            userList = userService.searchByKey(key);
        }
        else {
            userList = userService.findAll();
        }
        req.setAttribute("userList", userList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/user/list.jsp");
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
            case "list":
                showListPage(req, resp);
                break;
            case "search":
                doSearchByKey(req, resp);
                break;
            default:
                showListPage(req, resp);
                break;
        }
    }

    public void doCreate(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        User user;
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/user/create.jsp");
        String userName = req.getParameter("userName").trim();
        String password = req.getParameter("password").trim();
        String fullName = req.getParameter("full_name").trim();
        String phone = req.getParameter("phone");
        String age = String.valueOf(Integer.parseInt(req.getParameter("age").trim()));
        String email = req.getParameter("email");
        String address = req.getParameter("address").trim();
        String role = req.getParameter("role").trim();
        String img = req.getParameter("img").trim();


        List<String> errors = new ArrayList<>();

        boolean isPhone = ValidateUtils.isPhoneValid(phone);
        boolean isAge = ValidateUtils.isNumberValid(String.valueOf(age));
        boolean isEmail = ValidateUtils.isEmailValid(email);

        user = new User(userName, password, fullName, phone, age, address, email, role, img);
        if (userName.isEmpty() ||
                password.isEmpty() ||
                fullName.isEmpty() ||
                phone.isEmpty() ||
                age.isEmpty() ||
                email.isEmpty() ||
                address.isEmpty() ||
                role.isEmpty() ||
                img.isEmpty()) {
            errors.add("Vui lòng nhập đầy đủ thông tin!");
        }
        else if (userName.isEmpty()) {
            errors.add("");
        }
        if (userName.isEmpty()) {
            errors.add("UserName không được để trống!");
        }
        if (password.isEmpty()) {
            errors.add("Password không được để trống!");
        }
        if (fullName.isEmpty()) {
            errors.add("Fullname không được để trống!");
        }
        if (age.isEmpty()) {
            errors.add("Age không được để trống!");
        }
        if (!isAge) {
            errors.add("Age không đúng định dạng!");
        }
        if (phone.isEmpty()) {
            errors.add("Phone Nhập vào không đúng!");
        }
        if (!isPhone) {
            errors.add("Phone không đúng định dạng!");
        }
        if (email.isEmpty()) {
            errors.add("Email nhập vào không đúng!");
        }
        if (!isEmail) {
            errors.add("Email nhập vào không đúng dịnh dạng!");
        }
        if (address.isEmpty()) {
            errors.add("Address không được để trống!");
        }
        if (role.isEmpty()) {
            errors.add("Role không được chọn!");
        }
        if (img.isEmpty()) {
            errors.add("URL không được để trống!!");
        }
        if (userService.existsByEmail(email)) {
            errors.add("Email đã tồn tại!");
        }
        if (!role.equals("ADMIN") && !role.equals("USER")) {
            errors.add("Role phải là ADMIN hoặc User");
        } else if (errors.size() == 0) {
            user = new User(userName, password, fullName, phone, age, address, email, role, img);
            boolean success = false;
            success = userService.create(user);

            if (success) {
                req.setAttribute("success", true);
            } else {
                req.setAttribute("errors", true);
                errors.add("Dữ liệu không hợp lệ, vui lòng kiểm tra lại! ");
            }
        }
        if (errors.size() > 0) {
            req.setAttribute("errors", errors);
            req.setAttribute("userCreate", user);
        }
        dispatcher.forward(req, resp);
    }

    public void doUpdate(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
            User updateUser;
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/user/edit.jsp");
        int userId = Integer.parseInt(req.getParameter("id").trim());
        String fullName = req.getParameter("full_name").trim();
        String age = String.valueOf(Integer.parseInt(req.getParameter("age").trim()));
        String address = req.getParameter("address").trim();
        String img = req.getParameter("img").trim();

        List<String> errors = new ArrayList<>();

        updateUser = new User(userId, fullName, age, address, img);
        if (fullName.isEmpty() ||
                age.isEmpty() ||
                address.isEmpty() ||
                img.isEmpty()) {
            errors.add("Vui lòng nhập đủ thông tin!");
        }
        if (fullName.isEmpty()) {
            errors.add("Full name không được để trống!");
        }
        if (age.isEmpty()) {
            errors.add("Age không được để trống!");
        }
        if (address.isEmpty()) {
            errors.add("Address không được để trống!");
        }
        if (img.isEmpty()) {
            errors.add("Image không được để trống!");
        } else if (errors.size() == 0) {
            updateUser = new User(userId, fullName, age, address, img);
            boolean success = false;
            success = userService.update(updateUser);
            if (success){
                req.setAttribute("success", true);
            } else {
                req.setAttribute("errors", true);
                errors.add("Dữ liệu không đúng, vui lòng kiểm tra lại!");
            }
        }
        if (errors.size() > 0) {
            req.setAttribute("errors", errors);
            req.setAttribute("user", updateUser);
        }
        dispatcher.forward(req,resp);


    }

    public void showCreatePage(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/user/create.jsp");
        List<User> userList = userService.findAll();
        req.setAttribute("userList", userList);
        dispatcher.forward(req, resp);
    }

    private void showListPage(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("user/list.jsp");
        List<User> userList = userService.findAll();
        req.setAttribute("userList", userList);
        dispatcher.forward(req, resp);
    }

    private void showEditPage(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
            int userId = Integer.parseInt(req.getParameter("id"));
            User user = userService.findById(userId);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/cp/user/edit.jsp");
            req.setAttribute("user", user);
            dispatcher.forward(req,resp);

    }
}
