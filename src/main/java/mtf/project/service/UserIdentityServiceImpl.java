package mtf.project.service;

import mtf.project.model.*;
import mtf.project.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
@Transactional
public class UserIdentityServiceImpl implements UserIdentityService {

    @Autowired
    private UserIdentityDb userIdentityDb;


    @Override
    public UserIdentityModel addUserIdentity(UserIdentityModel user) {
        user.setNama("Elfira");
        user.setTujuan("berenang");
        return userIdentityDb.save(user);
    }


}