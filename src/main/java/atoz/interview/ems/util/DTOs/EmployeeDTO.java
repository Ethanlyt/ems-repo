package atoz.interview.ems.util.DTOs;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {
    private Long id;
    private String name;
    private String position;
    private DepartmentDTO department;
    private List<ProjectDTO> projects;

}
