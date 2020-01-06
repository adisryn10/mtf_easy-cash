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
public class JaminanServiceImpl implements JaminanService {

    @Autowired
    private JaminanDb jaminanDb;

    @Override
	public List<JaminanModel> getAllJaminan() {
		return jaminanDb.findAll();
    }

    @Override
    public JaminanModel getJaminanById(String id) {
        return jaminanDb.findById(id);
    }

    @Override
    public JaminanModel addJaminan(JaminanModel barangJaminan) {
        return jaminanDb.save(barangJaminan);
    }


}