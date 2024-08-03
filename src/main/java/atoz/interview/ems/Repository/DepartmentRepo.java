package atoz.interview.ems.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import atoz.interview.ems.Entity.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {

    Optional<Department> findByName(String name);
    
}
