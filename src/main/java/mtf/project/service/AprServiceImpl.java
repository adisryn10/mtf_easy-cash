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
public class AprServiceImpl implements AprService {

    @Autowired
    private AprDb aprDb;

    @Override
    public List<AprModel> getAllApr() {
        // TODO Auto-generated method stub
        return aprDb.findAll();
    }
}