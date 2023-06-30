package hr.tvz.iskra.studapp.repository;

import hr.tvz.iskra.studapp.command.StudentCommand;
import hr.tvz.iskra.studapp.domain.Student;
import hr.tvz.iskra.studapp.dto.StudentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StudentRepository {
    Set<Student> findAll();

    Optional<Student> findStudentByJMBAG(String JMBAG);

    Optional<Student> save (Student student);

    Optional<Student> update(String JMBAG, Student updatedStudent);

    void deleteByJMBAG(String JMBAG);
}
