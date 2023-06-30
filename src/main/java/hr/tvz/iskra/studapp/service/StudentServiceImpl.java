package hr.tvz.iskra.studapp.service;

import hr.tvz.iskra.studapp.command.StudentCommand;
import hr.tvz.iskra.studapp.domain.Student;
import hr.tvz.iskra.studapp.dto.StudentDto;
import hr.tvz.iskra.studapp.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private static final int YEARS_AFTER_WHICH_TUITION_SHOULD_BE_PAYED = 26;

    private StudentRepository studentRepository;

    private Student mapCommandToStudent(final StudentCommand studentCommand) {
        return new Student(studentCommand.getFirstName(), studentCommand.getLastName(), studentCommand.getJMBAG(), LocalDate.now().minusYears(32), studentCommand.getNumberOfECTS());
    }

    private boolean shouldTuitionBePayed(LocalDate dateOfBirth){
        return dateOfBirth.plusYears(YEARS_AFTER_WHICH_TUITION_SHOULD_BE_PAYED).isBefore(LocalDate.now());
    }

    private StudentDto mapStudentToDTO(final Student student){
        return new StudentDto(student.getJmbag(), student.getNumberOfECTS(), shouldTuitionBePayed(student.getDateOfBirth()), student.getFirstName(), student.getLastName());
    }

    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll().stream().map(this::mapStudentToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<StudentDto> findStudentByJMBAG(String JMBAG) {
        return studentRepository.findStudentByJMBAG(JMBAG).map(this::mapStudentToDTO);
    }

    @Override
    public Optional<StudentDto> save(final StudentCommand studentCommand) {
        return studentRepository.save(mapCommandToStudent(studentCommand)).map(this::mapStudentToDTO);
    }

    @Override
    public Optional<StudentDto> update(final String JMBAG, final StudentCommand updatedStudentCommand) {
        return studentRepository.update(JMBAG, mapCommandToStudent(updatedStudentCommand)).map(this::mapStudentToDTO);
    }

    @Override
    public void deleteByJMBAG(String JMBAG) {
        studentRepository.deleteByJMBAG(JMBAG);
    }
}
