package atoz.interview.ems.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import atoz.interview.ems.Entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    Optional<Employee> findByName(String name);

    List<Employee> findAllByPosition(String position);

    List<Employee> findAllByDepartmentId(Long id);

    @Query("SELECT e FROM Employee e JOIN e.projects p WHERE p.id = :projectId")
    List<Employee> findAllEmployeesByProjectId(Long projectId);

}
