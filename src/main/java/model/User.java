package model;

public class User {
    private int userId;
    private String userName;
    private String password;
    private String fullName;
    private String phone;
    private int age;
    private String email;
    private String address;
    private String Role;
    private String updatedAT;
    private String createdAT;
    private String img;

    public User() {
    }

    public User(int userId, String userName, String password, String fullName, String phone, int age, String email, String address, String role, String updatedAT, String createdAT, String img) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.age = age;
        this.email = email;
        this.address = address;
        Role = role;
        this.updatedAT = updatedAT;
        this.createdAT = createdAT;
        this.img = img;
    }


    public User(int userId, String fullName, String phone, int age, String email, String address, String img) {
        this.userId = userId;
        this.fullName = fullName;
        this.phone = phone;
        this.age = age;
        this.email = email;
        this.address = address;
        this.img = img;
    }

    public User(int userId, String userName, String fullName, String phone, int age, String email, String address, String role, String updatedAT, String createdAT, String img) {
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.phone = phone;
        this.age = age;
        this.email = email;
        this.address = address;
        Role = role;
        this.updatedAT = updatedAT;
        this.createdAT = createdAT;
        this.img = img;
    }

    public User(String userName, String password, String fullName, String phone, int age, String email, String address, String role, String img) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.age = age;
        this.email = email;
        this.address = address;
        Role = role;
        this.img = img;
    }

    public User(String userName, String password, String fullName, String phone, String age, String address, String role, String img) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.age = Integer.parseInt(age);
        this.email = email;
        Role = role;
        this.img = img;

    }

    public User(String userName, String password, String fullName, String phone, String age, String address, String email, String role, String img) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.age = Integer.parseInt(age);
        this.address = address;
        this.email = email;
        Role = role;
        this.img = img;
    }

    public User(int userId, String fullName, String age, String address, String img) {
        this.userId = userId;
        this.fullName = fullName;
        this.age = Integer.parseInt(age);
        this.address = address;
        this.img = img;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getUpdatedAT() {
        return updatedAT;
    }

    public void setUpdatedAT(String updatedAT) {
        this.updatedAT = updatedAT;
    }

    public String getCreatedAT() {
        return createdAT;
    }

    public void setCreatedAT(String createdAT) {
        this.createdAT = createdAT;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
