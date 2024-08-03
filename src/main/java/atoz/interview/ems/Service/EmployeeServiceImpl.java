package atoz.interview.ems.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import atoz.interview.ems.Entity.Department;
import atoz.interview.ems.Entity.Employee;
import atoz.interview.ems.Entity.Project;
import atoz.interview.ems.Repository.DepartmentRepo;
import atoz.interview.ems.Repository.EmployeeRepo;
import atoz.interview.ems.Repository.ProjectRepo;
import atoz.interview.ems.util.CustomDataAccessError;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepository;
    @Autowired
    private ProjectRepo projectRepository;
    @Autowired
    private DepartmentRepo departmentRepository;

    // Logic Implementation
    
    @Override
    public Employee savEmployee(Employee employee) {
        try {
            return employeeRepository.save(employee);
        } catch (DataAccessException e) {
            throw new CustomDataAccessError("Failed to save", e);
        }
    }

    @Override
    public Employee fetchEmployeeById(Long id) {
        try {
            return employeeRepository.findById(id).get();
        } catch (DataAccessException e) {
            throw new CustomDataAccessError("Failed to fetch", e);
        }    }

    @Override
    public List<Employee> fetchAllEmployee() {
        try {
            return employeeRepository.findAll();
        } catch (DataAccessException e) {
            throw new CustomDataAccessError("Failed to fetch", e);
        }
    }

    @Override
    public List<Employee> fetchAllEmployeeByPosition(String position) {
        try {
            return employeeRepository.findAllByPosition(position);
        } catch (Exception e) {
            throw new CustomDataAccessError("Failed to fetch", e);
        }
    }

    @Override
    public List<Employee> fetchAllEmployeeByDepartmentId(Long id) {
        try {
            return employeeRepository.findAllByDepartmentId(id);
        } catch (Exception e) {
            throw new CustomDataAccessError("Failed to fetch", e);
        }
    }

    @Override
    public List<Employee> fetchEmployeeByProjectId(Long id) {
        try {
            Project projectDb = projectRepository.findById(id).get();
            return employeeRepository.findAllEmployeesByProjectId(projectDb.getId());
        } catch (Exception e) {
            throw new CustomDataAccessError("Failed to fetch", e);
        }
    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        try {
            Employee empDb = employeeRepository.findById(id).get();
            if (Objects.nonNull(employee.getName()) && !"".equalsIgnoreCase(employee.getName())) {
                empDb.setName(employee.getName());
            }
            if (Objects.nonNull(employee.getPosition()) && !"".equalsIgnoreCase(employee.getPosition())) {
                empDb.setPosition(employee.getPosition());
            }
            if (Objects.nonNull(employee.getDepartment())) {
                Department depDb = departmentRepository.findById(employee.getDepartment().getId()).orElseThrow(() -> new CustomDataAccessError("Department not found", null));
                empDb.setDepartment(depDb);
            }
           if (Objects.nonNull(employee.getProjects())) {
            List<Project> updatedProjects = new ArrayList<>();
            for (Project project : employee.getProjects()) {
                Project projDb = projectRepository.findById(project.getId()).orElseThrow(() -> new CustomDataAccessError("Project not found", null));
                updatedProjects.add(projDb);
            }
            empDb.setProjects(updatedProjects);
        }
            return employeeRepository.save(empDb);
        } catch (Exception e) {
            throw new CustomDataAccessError("Failed to update", e);
        }
    }

    @Override
    public void deleteEmployeeById(Long id) {
        try {
            employeeRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new CustomDataAccessError("Failed to delete", e);
        }
    }

}
