package atoz.interview.ems.Service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import atoz.interview.ems.Entity.Department;
import atoz.interview.ems.Entity.Employee;
import atoz.interview.ems.Repository.DepartmentRepo;
import atoz.interview.ems.Repository.EmployeeRepo;
import atoz.interview.ems.util.CustomDataAccessError;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepository;
    @Autowired
    private EmployeeRepo employeeRepository;

    // Logic Implementation
    
    @Override
    public Department savDepartment(Department department) {
        try {
            return departmentRepository.save(department);
        } catch (DataAccessException e) {
            throw new CustomDataAccessError("Failed to save", e);
        }
    }

    @Override
    public Department fetchDepartmentById(Long id) {
        try {
            return departmentRepository.findById(id).get();
        } catch (DataAccessException e) {
            throw new CustomDataAccessError("Failed to fetch", e);
        }
    }

    @Override
    public Department fetchDepartmentByName(String name) {
        try {
            return departmentRepository.findByName(name).get();
        } catch (DataAccessException e) {
            throw new CustomDataAccessError("Failed to fetch", e);
        }
    }

    @Override
    public List<Department> fetchAllDepartment() {
        try {
            return departmentRepository.findAll();
        } catch (DataAccessException e) {
            throw new CustomDataAccessError("Failed to fetch", e);
        }
    }

    @Override
    public Department updateDepartment(Department department, Long id) {
        try {
            Department depDb = departmentRepository.findById(id).get();
            if (Objects.nonNull(department.getName()) && !"".equalsIgnoreCase(department.getName())) {
                depDb.setName(department.getName());
            }

            return departmentRepository.save(depDb);
        } catch (DataAccessException e) {
            throw new CustomDataAccessError("Failed to update", e);
        }
    }

    @Override
    public void deleteDepartmentById(Long id) {
        try {
            List<Employee> employees = employeeRepository.findAllByDepartmentId(id);
            for (Employee employee : employees) {
                employee.setDepartment(null);
                employeeRepository.save(employee);
            }
            departmentRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new CustomDataAccessError("Failed to delete", e);
        }
    }

}
