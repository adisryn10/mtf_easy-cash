package mtf.project.service;

import mtf.project.model.KendaraanModel;
import mtf.project.repository.KendaraanDb;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class KendaraanServiceImpl implements KendaraanService {

    @Autowired
    private KendaraanDb kendaraanDb;

    @Override
    public KendaraanModel getKendaraanById(Long idKendaraan) {
        return kendaraanDb.findById(idKendaraan).get();
    }

    @Override
    public List<KendaraanModel> getAllKendaraan() {
        // TODO Auto-generated method stub
        return kendaraanDb.findAll();
    }


}