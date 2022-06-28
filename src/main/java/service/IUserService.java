package service;

import model.User;

import java.util.List;
import java.util.Map;

public interface IUserService extends IGeneralService<User> {
    User findById(int userId);

    boolean existsByEmail(String email);


    List<User> findAll();

    public List<User> searchByKey(String key);


}
