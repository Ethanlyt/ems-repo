package atoz.interview.ems.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import atoz.interview.ems.Entity.Project;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {

    Optional<Project> findByName(String name);
    
}
