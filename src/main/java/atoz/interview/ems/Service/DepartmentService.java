package atoz.interview.ems.Service;

import java.util.List;

import atoz.interview.ems.Entity.Department;

public interface DepartmentService {
    List<Department> fetchAllDepartment();

    Department savDepartment(Department department);

    Department fetchDepartmentById(Long id);

    Department fetchDepartmentByName(String name);

    Department updateDepartment(Department department, Long id);

    void deleteDepartmentById(Long id);

}
