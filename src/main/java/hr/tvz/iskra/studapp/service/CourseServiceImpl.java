package hr.tvz.iskra.studapp.service;

import hr.tvz.iskra.studapp.domain.Course;
import hr.tvz.iskra.studapp.dto.CourseDTO;
import hr.tvz.iskra.studapp.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseDTO> findAll() {
        return courseRepository.findAll().stream().map(this::mapCourseToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> findAllByStudentJmbag(String jmbag) {
        return courseRepository.findAllByStudents_Jmbag(jmbag).stream().map(this::mapCourseToDTO).collect(Collectors.toList());
    }

    private CourseDTO mapCourseToDTO(Course course) {
        return new CourseDTO(course.getName(), course.getNumberOfEcts());
    }
}
