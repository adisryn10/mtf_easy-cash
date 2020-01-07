package mtf.project.service;

import java.util.List;

import mtf.project.model.*;

public interface KendaraanService{
    KendaraanModel getKendaraanById(Long id);
    List<KendaraanModel> getAllKendaraan();
}