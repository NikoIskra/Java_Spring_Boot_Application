package hr.tvz.iskra.studapp.service;

import hr.tvz.iskra.studapp.command.StudentCommand;
import hr.tvz.iskra.studapp.dto.StudentDto;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentDto> findAll();

    Optional<StudentDto> findStudentByJMBAG(String JMBAG);

    Optional<StudentDto> save (StudentCommand studentCommand);

    Optional<StudentDto> update(String JMBAG, StudentCommand updatedStudentCommand);
    void deleteByJMBAG (String JMBAG);
}
