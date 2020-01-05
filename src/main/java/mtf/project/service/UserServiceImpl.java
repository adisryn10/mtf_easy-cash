package mtf.project.service;

import mtf.project.model.*;
import mtf.project.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDb userDb;

    @Override
	public List<UserRoleModel> getAllUser() {
		return userDb.findAll();
    }

    @Override
    public UserRoleModel getUserById(String id) {
        return userDb.findById(id);
    }
}