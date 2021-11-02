package buisnesslogic.Services;



import buisnesslogic.model.Dto.CourseFilterDto;
import buisnesslogic.model.Dto.Response;
import buisnesslogic.model.entity.Course;
import buisnesslogic.model.entity.Student;
import buisnesslogic.exceptions.AlreadyEntrolledException;
import buisnesslogic.exceptions.SendMessageExeption;

import java.util.List;

public interface CourseService {

    List<Course> getByArea(int areaId);
    List<Course> getFilteredCourses(CourseFilterDto courseFilterDto);
    Response addStudentToCourse(int courseId) throws SendMessageExeption, AlreadyEntrolledException;

    Course getById(int id);
    Iterable<Course> getAll();

}
