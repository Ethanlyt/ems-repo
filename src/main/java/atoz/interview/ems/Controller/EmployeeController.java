package atoz.interview.ems.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import atoz.interview.ems.Entity.Employee;
import atoz.interview.ems.Service.EmployeeService;
import atoz.interview.ems.util.CustomDataAccessError;
import atoz.interview.ems.util.DTOConverter;
import atoz.interview.ems.util.DTOs.EmployeeDTO;

@RestController
@RequestMapping("/employee")

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // CRUD Operation only
    // Create
    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        try {
            Employee employeeDb = employeeService.savEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeDb);
        } catch (CustomDataAccessError e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Read
    @GetMapping("/read")
    public ResponseEntity<?> fetchEmployeeById(@RequestParam(name = "id") Long id) {
        try {
            Employee employeeDb = employeeService.fetchEmployeeById(id);
            return ResponseEntity.status(HttpStatus.OK).body(DTOConverter.convertToDTO(employeeDb));
        } catch (CustomDataAccessError e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/readAll")
    public ResponseEntity<?> fetchAllEmployee(
            @RequestParam(name = "position", required = false) String position,
            @RequestParam(name = "departmentId", required = false) Long departmentId,
            @RequestParam(name = "projectId", required = false) Long projectId) {
        try {
            List<Employee> employeeDb = new ArrayList<>();
            if (position != null) {
                employeeDb = employeeService.fetchAllEmployeeByPosition(position);
            } else if (departmentId != null) {
                employeeDb = employeeService.fetchAllEmployeeByDepartmentId(departmentId);
            } else if (projectId != null) {
                employeeDb = employeeService.fetchEmployeeByProjectId(projectId);
            } else {
                employeeDb = employeeService.fetchAllEmployee();
            }
            List<EmployeeDTO> employeeDTOs = employeeDb.stream().map(DTOConverter::convertToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(employeeDTOs);
        } catch (CustomDataAccessError e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Update
    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(@RequestBody(required = true) Employee employee,
            @RequestParam(name = "id") Long id) {
        try {
            Employee employeeDb = employeeService.updateEmployee(employee, id);
            return ResponseEntity.status(HttpStatus.OK).body(DTOConverter.convertToDTO(employeeDb));
        } catch (CustomDataAccessError e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEmployeeById(@RequestParam(name = "id") Long id) {
        try {
            employeeService.deleteEmployeeById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Employee deleted successfully");
        } catch (CustomDataAccessError e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
