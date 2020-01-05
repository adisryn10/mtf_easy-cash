package mtf.project.service;

import java.util.List;

import mtf.project.model.*;

public interface UserService{
    UserRoleModel getUserById(String id);
    List<UserRoleModel> getAllUser();
}