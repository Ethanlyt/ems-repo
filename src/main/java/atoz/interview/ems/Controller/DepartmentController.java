package atoz.interview.ems.Controller;

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

import atoz.interview.ems.Entity.Department;
import atoz.interview.ems.Service.DepartmentService;
import atoz.interview.ems.util.CustomDataAccessError;
import atoz.interview.ems.util.DTOConverter;
import atoz.interview.ems.util.DTOs.DepartmentDTO;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    // CRUD Operation only
    // Craete
    @PostMapping("/create")
    public ResponseEntity<?> createDepartment(@RequestBody Department department) {
        try {
            Department departmentDb = departmentService.savDepartment(department);
            return ResponseEntity.status(HttpStatus.CREATED).body(departmentDb);
        } catch (CustomDataAccessError e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Read
    @GetMapping("/read/{id}")
    public ResponseEntity<?> fetchDepartmentById(@PathParam("id") Long id) {
        try {
            Department departmentDb = departmentService.fetchDepartmentById(id);
            return ResponseEntity.status(HttpStatus.OK).body(DTOConverter.convertToDTO(departmentDb));
        } catch (CustomDataAccessError e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/read/{name}")
    public ResponseEntity<?> fetchDepartmentByName(@PathParam("name") String name) {
        try {
            Department departmentDb = departmentService.fetchDepartmentByName(name);
            return ResponseEntity.status(HttpStatus.OK).body(DTOConverter.convertToDTO(departmentDb));
        } catch (CustomDataAccessError e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/readAll")
    public ResponseEntity<?> fetchAllDepartment() {
        try {
            List<Department> departmentDb = departmentService.fetchAllDepartment();
            List<DepartmentDTO> departmentDTOs = departmentDb.stream().map(DTOConverter::convertToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(departmentDTOs);
        } catch (CustomDataAccessError e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Update
    @PutMapping("/update")
    public ResponseEntity<?> updateDepartment(@RequestBody Department department, @RequestParam(name = "id") Long id) {
        try {
            Department departmentDb = departmentService.updateDepartment(department, id);
            return ResponseEntity.status(HttpStatus.OK).body(DTOConverter.convertToDTO(departmentDb));
        } catch (CustomDataAccessError e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteDepartmentById(@RequestParam(name = "id") Long id) {
        try {
            departmentService.deleteDepartmentById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Department deleted successfully");
        } catch (CustomDataAccessError e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
