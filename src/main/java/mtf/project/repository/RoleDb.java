package mtf.project.repository;

import mtf.project.model.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDb extends JpaRepository<RoleModel, Long> {
    <Optional>RoleModel findByNama(String nama);
}
