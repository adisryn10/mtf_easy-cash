package mtf.project.service;

import java.util.List;

import mtf.project.model.*;

public interface JaminanService{
    JaminanModel getJaminanById(String id);
    List<JaminanModel> getAllJaminan();
	JaminanModel addJaminan(JaminanModel barangJaminan);
}