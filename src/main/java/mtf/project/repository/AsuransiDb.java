package mtf.project.repository;

import mtf.project.model.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsuransiDb extends JpaRepository<AsuransiModel, Long> {
    Optional<AsuransiModel> findById(Long idAsuransi);
    List<AsuransiModel> findAll();
}
