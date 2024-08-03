package atoz.interview.ems.Service;

import java.util.List;

import atoz.interview.ems.Entity.Project;

public interface ProjectService {
    Project savProject(Project project);
    List<Project> fetchAllProject();
    Project fetchProjectById(Long id);
    Project fetchProjectByName(String name);
    Project updateProject(Project project, Long id);
    void deleteProjectById(Long id);
}
