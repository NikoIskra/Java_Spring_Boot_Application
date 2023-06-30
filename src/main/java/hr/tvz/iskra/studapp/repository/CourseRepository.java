package hr.tvz.iskra.studapp.repository;

import hr.tvz.iskra.studapp.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByStudents_Jmbag(String jmbag);
}
