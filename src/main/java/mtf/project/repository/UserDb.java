package mtf.project.repository;

import mtf.project.model.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDb extends JpaRepository<UserRoleModel, Long> {
    UserRoleModel findById(String uuid);
    UserRoleModel findByUsername(String username);
    List<UserRoleModel> findAll();
}
