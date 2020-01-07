package mtf.project.service;

import mtf.project.model.AsuransiModel;
import mtf.project.repository.AsuransiDb;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AsuransiServiceImpl implements AsuransiService {

    @Autowired
    private AsuransiDb asuransiDb;

    @Override
    public AsuransiModel getAsuransiById(Long idAsuransi) {
        return asuransiDb.findById(idAsuransi).get();
    }

    @Override
    public List<AsuransiModel> getAllAsuransi() {
        // TODO Auto-generated method stub
        return asuransiDb.findAll();
    }


}