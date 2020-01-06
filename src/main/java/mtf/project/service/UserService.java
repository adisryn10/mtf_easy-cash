package mtf.project.service;

import java.util.List;

import mtf.project.model.*;

public interface UserService{
    UserRoleModel getUserById(String id);
    public String encrypt(String password);
    List<UserRoleModel> getAllUser();
	UserRoleModel addUser(UserRoleModel user);
}