package hr.tvz.iskra.studapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String jmbag;
    private LocalDate dateOfBirth;

    @Column(name="ects_points")
    private Integer numberOfECTS;

    public Student(Long id, String firstName, String lastName, String JMBAG, LocalDate dateOfBirth, Integer numberOfECTS) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbag = JMBAG;
        this.dateOfBirth = dateOfBirth;
        this.numberOfECTS = numberOfECTS;
    }

    public Student(String firstName, String lastName, String JMBAG, LocalDate dateOfBirth, Integer numberOfECTS) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbag = JMBAG;
        this.dateOfBirth = dateOfBirth;
        this.numberOfECTS = numberOfECTS;
    }

    @ManyToMany(targetEntity = Course.class)
    @JoinTable(
            name = "student_course",
            joinColumns = {@JoinColumn(name="student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    private List<Course> courses;
}
