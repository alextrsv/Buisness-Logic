package buisnesslogic.Services.ServicesImpl;

import buisnesslogic.Dto.CourseFilterDto;
import buisnesslogic.Dto.Response;
import buisnesslogic.Repositories.CategoryRepository;
import buisnesslogic.Repositories.CourseRepository;
import buisnesslogic.Repositories.StudentRepository;
import buisnesslogic.Services.CourseService;
import buisnesslogic.business.MailSender;
import buisnesslogic.entity.Category;
import buisnesslogic.entity.Course;
import buisnesslogic.entity.Student;
import buisnesslogic.exceptions.AlreadyEntrolledException;
import buisnesslogic.exceptions.NoSuchCourseException;
import buisnesslogic.exceptions.SendMessageExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    StudentRepository studentRepository;


    @Override
    public Course getById(int id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public Iterable<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getByArea(int areaId) {
        Category category = categoryRepository.findById(areaId).get();
        List<Course> courses = new ArrayList<>();
        Iterable<Course> allCourses = courseRepository.findAll();///////////+iterable//////////////////////////////////////////////////////////////////////////===========================

        for (Course course: allCourses) {
            for (Category cat: course.getCategories()) {
                if(cat.getArea().equals(category.getArea())){
                    courses.add(course);
                    break;
                }
            }
        }
        return courses;
    }

    @Override
    public List<Course> getFilteredCourses(CourseFilterDto courseFilterDto) {
        List<Course> filteredCourses = new ArrayList<>();

        Iterable<Course> allCourses;///////////////iterable//////////////////////////////////////////////////////////////////===========================
        if (courseFilterDto.getCategory().getArea() != null && courseFilterDto.getCategory().getTitle() != null) //если указана и area и categoty
            allCourses = categoryRepository.findByTitle(courseFilterDto.getCategory().getTitle()).getCourses();
        else if (courseFilterDto.getCategory().getArea() != null && courseFilterDto.getCategory().getTitle() == null) //если указана только area
            allCourses = getByArea( categoryRepository.findAreaByName(courseFilterDto.getCategory().getArea()).get(0).getCat_id());
        else allCourses = courseRepository.findAll();//////////////////////////////////////////////////////////////////////////////////////===========================

        int duration;

        for (Course course : allCourses) {
            duration = course.getEndDate().getMonth() - course.getStatDate().getMonth();

            if (courseFilterDto.getLeftDurationBorder() == courseFilterDto.getRightDurationBorder() ||
                    duration >= courseFilterDto.getLeftDurationBorder() && duration <= courseFilterDto.getRightDurationBorder()) {

                for (int level : courseFilterDto.getLevels()) {
                    if (level ==0 || level == course.getLevel()){

                        for (String pltfrm: courseFilterDto.getPlatforms()) {
                            if(pltfrm.equals("") || pltfrm.equals(course.getPlatform())){

                                if(courseFilterDto.getMarkLeftBorder() == courseFilterDto.getMarkRightBorder() || course.getMark() >= courseFilterDto.getMarkLeftBorder() && course.getMark() <= courseFilterDto.getMarkRightBorder())
                                    filteredCourses.add(course);
                                break;
                            }
                        }
                    }
                }
            } else break;
        }

        return filteredCourses;
    }

    @Override
    public Response addStudentToCourse(Student newStudent, int courseId) throws NoSuchCourseException, SendMessageExeption, AlreadyEntrolledException {
        Course course;
        try {
            course = courseRepository.findById(courseId).get();
        }catch (NoSuchElementException exception){
            throw new NoSuchCourseException(courseId);
        }
        Student student = studentRepository.findByEmail(newStudent.getEmail());
        if(student == null)
            student = newStudent;
        if (course.getStudents().contains(student)) throw new AlreadyEntrolledException();
        else {
            course.getStudents().add(student);
            courseRepository.save(course);
            MailSender.makeSend(student.getEmail(), course);
        }

        return new Response("user has been entrolled to course" + course.getTitle());
    }


}
