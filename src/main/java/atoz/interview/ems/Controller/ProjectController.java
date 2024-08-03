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

import atoz.interview.ems.Entity.Project;
import atoz.interview.ems.Service.ProjectService;
import atoz.interview.ems.util.CustomDataAccessError;
import atoz.interview.ems.util.DTOConverter;
import atoz.interview.ems.util.DTOs.ProjectDTO;
import jakarta.websocket.server.PathParam;


@RestController
@RequestMapping("/project")

public class ProjectController {
    @Autowired
    private ProjectService projectService;
    // CRUD Operation only
    // Create
    @PostMapping("/create")
    public ResponseEntity<?> createProject(@RequestBody Project project) {
        try {
            Project projectDb = projectService.savProject(project);
            return ResponseEntity.status(HttpStatus.CREATED).body(projectDb);
        } catch (CustomDataAccessError e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    // Read
    @GetMapping("/readAll")
    public ResponseEntity<?> fetchAllProject() {
        try {
            List<Project> projectDb = projectService.fetchAllProject();
            List<ProjectDTO> projectDTOs = projectDb.stream().map(DTOConverter::convertToDTO).collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(projectDTOs);
        } catch (CustomDataAccessError e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<?> fetchProjectById(@PathParam("id") Long id) {
        try {
            Project projectDb = projectService.fetchProjectById(id);
            return ResponseEntity.status(HttpStatus.OK).body(DTOConverter.convertToDTO(projectDb));
        } catch (CustomDataAccessError e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    // Update
    @PutMapping("/update")
    public ResponseEntity<?> updateProject(@RequestBody(required = true) Project project, @RequestParam(name = "id") Long id) {
        try {
            Project projectDb = projectService.updateProject(project, id);
            return ResponseEntity.status(HttpStatus.OK).body(DTOConverter.convertToDTO(projectDb));
        } catch (CustomDataAccessError e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProjectById(@RequestParam(name = "id") Long id) {
        try {
            projectService.deleteProjectById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Project Deleted");
        } catch (CustomDataAccessError e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
