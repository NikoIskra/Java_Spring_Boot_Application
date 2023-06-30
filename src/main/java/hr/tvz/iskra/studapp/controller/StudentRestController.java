package hr.tvz.iskra.studapp.controller;

import hr.tvz.iskra.studapp.command.StudentCommand;
import hr.tvz.iskra.studapp.domain.Student;
import hr.tvz.iskra.studapp.dto.StudentDto;
import hr.tvz.iskra.studapp.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("students")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class StudentRestController {

    private StudentService studentService;

    @GetMapping
    public List<StudentDto> getAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("/{JMBAG}")
    public ResponseEntity<StudentDto> getStudentByJMBAG(@PathVariable final String JMBAG){
        return studentService.findStudentByJMBAG(JMBAG)
                .map(
                        studentDTO -> ResponseEntity
                                .status(HttpStatus.OK)
                                .body(studentDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<StudentDto> save(@RequestBody @Valid  StudentCommand command){
        return studentService.save(command)
                .map(
                        studentDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(studentDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{JMBAG}")
    public ResponseEntity<StudentDto> update(@PathVariable String JMBAG, @Valid @RequestBody final StudentCommand updateStudentCommand){
        return studentService.update(JMBAG, updateStudentCommand)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{JMBAG}")
    public void delete(@PathVariable String JMBAG){
        studentService.deleteByJMBAG(JMBAG);
    }

}
