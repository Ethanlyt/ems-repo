package atoz.interview.ems.Service;

import java.util.List;

import atoz.interview.ems.Entity.Employee;

public interface EmployeeService {
    Employee savEmployee(Employee employee);

    Employee fetchEmployeeById(Long id);

    List<Employee> fetchAllEmployee();

    List<Employee> fetchAllEmployeeByPosition(String position);

    List<Employee> fetchAllEmployeeByDepartmentId(Long id);

    List<Employee> fetchEmployeeByProjectId(Long id);

    Employee updateEmployee(Employee employee, Long id);

    void deleteEmployeeById(Long id);
}
