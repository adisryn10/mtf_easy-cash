package mtf.project.repository;

import mtf.project.model.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JaminanDb extends JpaRepository<JaminanModel, Long> {
    JaminanModel findById(String uuid);
    JaminanModel findByMerk(String merk);
    List<JaminanModel> findAll();
}
