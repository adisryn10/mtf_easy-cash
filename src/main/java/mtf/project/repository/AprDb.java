package mtf.project.repository;

import mtf.project.model.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AprDb extends JpaRepository<AprModel, Long> {
    List<AprModel> findAll();
}
