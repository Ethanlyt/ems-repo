package atoz.interview.ems.Service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import atoz.interview.ems.Entity.Employee;
import atoz.interview.ems.Entity.Project;
import atoz.interview.ems.Repository.EmployeeRepo;
import atoz.interview.ems.Repository.ProjectRepo;
import atoz.interview.ems.util.CustomDataAccessError;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepo projectRepository;
    @Autowired
    private EmployeeRepo employeeRepository;

    // Logic Implementation

    @Override
    public Project savProject(Project project) {
        try {
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new CustomDataAccessError("Failed to save", e);
        }
    }

    @Override
    public List<Project> fetchAllProject() {
        try {
            return projectRepository.findAll();
        } catch (Exception e) {
            throw new CustomDataAccessError("Failed to fetch", e);
        }
    }

    @Override
    public Project fetchProjectById(Long id) {
        try {
            return projectRepository.findById(id).get();
        } catch (Exception e) {
            throw new CustomDataAccessError("Failed to fetch", e);
        }
    }

    @Override
    public Project fetchProjectByName(String name) {
        try {
            return projectRepository.findByName(name).get();
        } catch (Exception e) {
            throw new CustomDataAccessError("Failed to fetch", e);
        }
    }

    @Override
    public Project updateProject(Project project, Long id) {
        try {
            Project projectDb = projectRepository.findById(id).get();
            if (Objects.nonNull(project.getName()) && !"".equalsIgnoreCase(project.getName())) {
                projectDb.setName(project.getName());
            }
            return projectRepository.save(projectDb);
        } catch (Exception e) {
            throw new CustomDataAccessError("Failed to update", e);
        }
    }

    @Override
    public void deleteProjectById(Long id) {
        try {
            List<Employee> employees = employeeRepository.findAllEmployeesByProjectId(id);
            for (Employee employee : employees) {
                employee.getProjects().remove(projectRepository.findById(id).get());
                employeeRepository.save(employee);
            }
            projectRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomDataAccessError("Failed to delete", e);
        }
    }
}
