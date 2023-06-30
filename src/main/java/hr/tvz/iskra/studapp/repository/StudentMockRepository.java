package hr.tvz.iskra.studapp.repository;

import hr.tvz.iskra.studapp.command.StudentCommand;
import hr.tvz.iskra.studapp.domain.Student;
import hr.tvz.iskra.studapp.dto.StudentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public class StudentMockRepository implements StudentRepository {

    @Override
    public Optional<Student> update(String JMBAG, Student updatedStudent) {
        return Optional.empty();
    }

    private Set<Student> studentList;

    public StudentMockRepository() {
        studentList=new HashSet<>();

        studentList.add(new Student(1L,"Iskra", "Niko", "0246094170", LocalDate.now().minusYears(21), 128));
        studentList.add(new Student(2L,"Horvat", "Dominik", "0237816942", LocalDate.now().minusYears(28), 137));

    }

    @Override
    public Set<Student> findAll() {
        return studentList;
    }

    @Override
    public Optional<Student> findStudentByJMBAG(String JMBAG) {
        return studentList.stream().filter(s -> Objects.equals(s.getJmbag(), JMBAG)).findAny();
    }

    @Override
    public Optional<Student> save(Student student) {
        if(!studentList.stream().map(Student::getJmbag).toList().contains(student.getJmbag())){
            studentList.add(student);
            return Optional.of(student);
        } else {
            return Optional.empty();
        }
    }


    @Override
    public void deleteByJMBAG(String JMBAG) {
        studentList.removeIf(student -> Objects.equals(student.getJmbag(), JMBAG));
    }

}
