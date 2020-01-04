package mtf.project.service;

import java.util.List;

import mtf.project.model.*;

public interface UserService{
    UserModel getUserById(String id);
    List<UserModel> getAllUser();
}