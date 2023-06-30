package hr.tvz.iskra.studapp.controller;

import hr.tvz.iskra.studapp.dto.CourseDTO;
import hr.tvz.iskra.studapp.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("course")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;


    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseService.findAll();
    }

    @GetMapping(params = "jmbag")
    public List<CourseDTO> getAllCoursesByStudentsJmbag(@RequestParam String jmbag) {
        return courseService.findAllByStudentJmbag(jmbag);
    }
}
