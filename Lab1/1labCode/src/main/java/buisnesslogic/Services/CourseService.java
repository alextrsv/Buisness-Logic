package buisnesslogic.Services;



import buisnesslogic.Dto.CourseFilterDto;
import buisnesslogic.Dto.Response;
import buisnesslogic.entity.Course;
import buisnesslogic.entity.Student;
import buisnesslogic.exceptions.AlreadyEntrolledException;
import buisnesslogic.exceptions.SendMessageExeption;

import java.util.List;

public interface CourseService {

    List<Course> getByArea(int areaId);
    List<Course> getFilteredCourses(CourseFilterDto courseFilterDto);
    Response addStudentToCourse(Student newStudent, int courseId) throws SendMessageExeption, AlreadyEntrolledException;

    Course getById(int id);
    Iterable<Course> getAll();

}
