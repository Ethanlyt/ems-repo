package atoz.interview.ems.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PROJECT")
public class Project {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "projects")
    private List<Employee> employees;
}
