package mtf.project.service;

import mtf.project.model.AsuransiModel;
import mtf.project.model.PembayaranModel;
import mtf.project.repository.AsuransiDb;
import mtf.project.repository.PembayaranDb;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PembayaranServiceImpl implements PembayaranService {

    @Autowired
    private PembayaranDb pembayaranDb;

    @Override
    public PembayaranModel getPembayaranById(Long idPembayaran) {
        // TODO Auto-generated method stub
        return pembayaranDb.findById(idPembayaran).get();
    }


}