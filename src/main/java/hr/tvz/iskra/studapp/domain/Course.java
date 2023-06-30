package hr.tvz.iskra.studapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(name="ects_points")
    private Integer numberOfEcts;

    @ManyToMany(targetEntity = Student.class, mappedBy = "courses")
    private List<Student> students;

}
