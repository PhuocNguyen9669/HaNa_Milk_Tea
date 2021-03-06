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
            errors.add("Vui l??ng nh???p ?????y ????? th??ng tin!");
        }
        else if (userName.isEmpty()) {
            errors.add("");
        }
        if (userName.isEmpty()) {
            errors.add("UserName kh??ng ???????c ????? tr???ng!");
        }
        if (password.isEmpty()) {
            errors.add("Password kh??ng ???????c ????? tr???ng!");
        }
        if (fullName.isEmpty()) {
            errors.add("Fullname kh??ng ???????c ????? tr???ng!");
        }
        if (age.isEmpty()) {
            errors.add("Age kh??ng ???????c ????? tr???ng!");
        }
        if (!isAge) {
            errors.add("Age kh??ng ????ng ?????nh d???ng!");
        }
        if (phone.isEmpty()) {
            errors.add("Phone Nh???p v??o kh??ng ????ng!");
        }
        if (!isPhone) {
            errors.add("Phone kh??ng ????ng ?????nh d???ng!");
        }
        if (email.isEmpty()) {
            errors.add("Email nh???p v??o kh??ng ????ng!");
        }
        if (!isEmail) {
            errors.add("Email nh???p v??o kh??ng ????ng d???nh d???ng!");
        }
        if (address.isEmpty()) {
            errors.add("Address kh??ng ???????c ????? tr???ng!");
        }
        if (role.isEmpty()) {
            errors.add("Role kh??ng ???????c ch???n!");
        }
        if (img.isEmpty()) {
            errors.add("URL kh??ng ???????c ????? tr???ng!!");
        }
        if (userService.existsByEmail(email)) {
            errors.add("Email ???? t???n t???i!");
        }
        if (!role.equals("ADMIN") && !role.equals("USER")) {
            errors.add("Role ph???i l?? ADMIN ho???c User");
        } else if (errors.size() == 0) {
            user = new User(userName, password, fullName, phone, age, address, email, role, img);
            boolean success = false;
            success = userService.create(user);

            if (success) {
                req.setAttribute("success", true);
            } else {
                req.setAttribute("errors", true);
                errors.add("D??? li???u kh??ng h???p l???, vui l??ng ki???m tra l???i! ");
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
            errors.add("Vui l??ng nh???p ????? th??ng tin!");
        }
        if (fullName.isEmpty()) {
            errors.add("Full name kh??ng ???????c ????? tr???ng!");
        }
        if (age.isEmpty()) {
            errors.add("Age kh??ng ???????c ????? tr???ng!");
        }
        if (address.isEmpty()) {
            errors.add("Address kh??ng ???????c ????? tr???ng!");
        }
        if (img.isEmpty()) {
            errors.add("Image kh??ng ???????c ????? tr???ng!");
        } else if (errors.size() == 0) {
            updateUser = new User(userId, fullName, age, address, img);
            boolean success = false;
            success = userService.update(updateUser);
            if (success){
                req.setAttribute("success", true);
            } else {
                req.setAttribute("errors", true);
                errors.add("D??? li???u kh??ng ????ng, vui l??ng ki???m tra l???i!");
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
