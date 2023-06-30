package hr.tvz.iskra.studapp.service;

import hr.tvz.iskra.studapp.domain.Course;
import hr.tvz.iskra.studapp.dto.CourseDTO;

import java.util.List;

public interface CourseService {
    List<CourseDTO> findAll();
    List<CourseDTO> findAllByStudentJmbag(String jmbag);
}
