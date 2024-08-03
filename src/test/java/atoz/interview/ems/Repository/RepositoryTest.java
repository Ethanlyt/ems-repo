package atoz.interview.ems.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import atoz.interview.ems.Entity.Department;
import atoz.interview.ems.Entity.Employee;
import atoz.interview.ems.Entity.Project;


@DataJpaTest
@ActiveProfiles("test")
@ComponentScan(basePackages = { "atoz.interview.ems.config" })
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RepositoryTest {

    @Autowired
    private EmployeeRepo employeeRepository;

    @Autowired
    private DepartmentRepo departmentRepository;

    @Autowired
    private ProjectRepo projectRepository;


    @Test
    @DisplayName("Test 1:Save Department Test")
    @Order(1)
    @Rollback(value = false)
    public void saveDepartmentTest() {
        Department department = new Department();
        department.setName("Department 1");
        departmentRepository.save(department);
        System.out.println(department);
        Assertions.assertThat(department.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test 2:Get Department Test")
    @Order(2)
    @Rollback(value = false)
    public void getDepartmentListTest() {
        Optional<Department> department = departmentRepository.findById(1L);
        System.out.println(department);
        Assertions.assertThat(department.get().getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Test 3:Save Project Test")
    @Order(3)
    @Rollback(value = false)
    public void saveProjectTest() {
        Project project = new Project();
        project.setName("Project 1");
        List<Employee> employees = new ArrayList<>();
        project.setEmployees(employees);
        projectRepository.save(project);
        System.out.println(project);
        Assertions.assertThat(project.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test 4:Get Project Test")
    @Order(4)
    @Rollback(value = false)
    public void getProjectListTest() {
        Project project = projectRepository.findById(1L).get();
        System.out.println(project);
        Assertions.assertThat(project.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Test 5:Save Employee Test")
    @Order(5)
    @Rollback(value = false)
    public void saveEmployeeTest() {
        Employee employee = new Employee();
        employee.setName("Employee 1");
        employee.setPosition("Software Developer");
        employeeRepository.save(employee);
        System.out.println(employee);
        Assertions.assertThat(employee.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Test 6:Get Employee Test")
    @Order(6)
    @Rollback(value = false)
    public void getEmployeeListTest() {
        Employee employee = employeeRepository.findById(1L).get();
        System.out.println(employee);
        Assertions.assertThat(employee.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Test 7:Update Employee Test")
    @Order(7)
    @Rollback(value = false)
    public void updateEmployeeTest() {
        Employee employee = employeeRepository.findById(1L).get();
        employee.setDepartment(departmentRepository.findById(1L).get());
        List<Project> projects = projectRepository.findAll();
        employee.setProjects(projects);
        Employee employeeUpdated = employeeRepository.save(employee);
        System.out.println(employeeUpdated);
        Assertions.assertThat(employeeUpdated.getDepartment().getId()).isEqualTo(1L);
        Assertions.assertThat(employeeUpdated.getProjects().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Test 8:Update Employee Test")
    @Order(8)
    @Rollback(value = false)
    public void deleteEmployeeTest() {
        employeeRepository.deleteById(1L);
        Optional<Employee> deletedEmployee = employeeRepository.findById(1L);
        List<Project> projects = projectRepository.findAll();
        Department department = departmentRepository.findById(1L).get();
        Assertions.assertThat(deletedEmployee).isEmpty();
        Assertions.assertThat(projects).isNotEmpty();
        Assertions.assertThat(department).isNotNull();
    }

}
