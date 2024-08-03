package atoz.interview.ems.util;

import java.util.stream.Collectors;

import atoz.interview.ems.Entity.Department;
import atoz.interview.ems.Entity.Employee;
import atoz.interview.ems.Entity.Project;
import atoz.interview.ems.util.DTOs.DepartmentDTO;
import atoz.interview.ems.util.DTOs.EmployeeDTO;
import atoz.interview.ems.util.DTOs.ProjectDTO;

public class DTOConverter {
    public static EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setPosition(employee.getPosition());
        if (employee.getDepartment() != null) {
            employeeDTO.setDepartment(convertToDTO(employee.getDepartment()));
        }

        if (employee.getProjects() != null) {
            employeeDTO.setProjects(employee.getProjects().stream()
                    .map(DTOConverter::convertToDTO)
                    .collect(Collectors.toList()));
        }
        return employeeDTO;
    }

    public static DepartmentDTO convertToDTO(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(department.getId());
        departmentDTO.setName(department.getName());
        return departmentDTO;
    }

    public static ProjectDTO convertToDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());

        return projectDTO;
    }
}
