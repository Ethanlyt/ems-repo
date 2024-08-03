package atoz.interview.ems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import atoz.interview.ems.Entity.Department;
import atoz.interview.ems.Entity.Employee;
import atoz.interview.ems.Entity.Project;
import atoz.interview.ems.Entity.User;
import atoz.interview.ems.Repository.DepartmentRepo;
import atoz.interview.ems.Repository.EmployeeRepo;
import atoz.interview.ems.Repository.ProjectRepo;
import atoz.interview.ems.Repository.UserRepo;

@SpringBootApplication
@ComponentScan(basePackages = { "atoz.interview.ems", "atoz.interview.ems.config" })
public class EmsApplication {

    @Autowired
    private EmployeeRepo employeeRepository;

    @Autowired
    private DepartmentRepo departmentRepository;

    @Autowired
    private ProjectRepo projectRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(EmsApplication.class, args);
    }

    @Bean
    @Profile("!test")
    public CommandLineRunner initializeData() {
        return args -> {
            if (userRepository.findAll().size() != 0) {
                return;
            } else {
                User user = new User();
                user.setName("admin");
                user.setPassword(passwordEncoder.encode("admin"));
                userRepository.save(user);

            }

            if (departmentRepository.findAll().size() != 0) {
                return;
            } else {
                Department dep1 = new Department();
                dep1.setName("Research and Development");
                departmentRepository.save(dep1);

                Department dep2 = new Department();
                dep2.setName("IT Support");
                departmentRepository.save(dep2);
            }

            if (projectRepository.findAll().size() != 0) {
                return;
            } else {
                Project proj1 = new Project();
                proj1.setName("Employee Management System");
                projectRepository.save(proj1);

                Project proj2 = new Project();
                proj2.setName("Project Management Tool");
                projectRepository.save(proj2);
            }

            if (employeeRepository.findAll().size() != 0) {
                return;

            } else {
                Employee emp1 = new Employee();
                emp1.setName("Ethan Leong");
                emp1.setPosition("Software Engineer");
                employeeRepository.save(emp1);

                Employee emp2 = new Employee();
                emp2.setName("Yu Kong");
                emp2.setPosition("Software Engineer");
                employeeRepository.save(emp2);

                Employee emp3 = new Employee();
                emp3.setName("John Doe");
                emp3.setPosition("Software Developer");
                employeeRepository.save(emp3);
            }

        };
    }

}
