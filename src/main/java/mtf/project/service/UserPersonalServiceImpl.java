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
public class UserPersonalServiceImpl implements UserPersonalService {

    @Autowired
    private UserPersonalDb userPersonalDb;

    @Override
    public UserPersonalModel addDataPribadi(UserPersonalModel userPersonal) {
        return userPersonalDb.save(userPersonal);
    }


}