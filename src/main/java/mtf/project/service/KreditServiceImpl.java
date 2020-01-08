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
public class KreditServiceImpl implements KreditService {

    @Autowired
    private KreditDb kreditDb;

    @Override
    public void addKreditModel(KreditModel kredit) {
        kreditDb.save(kredit);

    }

    @Override
    public void changeKreditStatus(KreditModel kreditModel) {
        // TODO Auto-generated method stub
        KreditModel targetKredit = kreditDb.findById(kreditModel.getId()).get();

        try{
            targetKredit.setStatus(targetKredit.getStatus());
            kreditDb.save(targetKredit);
        }
        catch(NullPointerException nullException){
            
        }

    }


}