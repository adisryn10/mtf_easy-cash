package mtf.project.service;

import java.util.List;

import mtf.project.model.*;

public interface AsuransiService{
    AsuransiModel getAsuransiById(Long id);
    List<AsuransiModel> getAllAsuransi();
}