package buisnesslogic.Services.ServicesImpl;

import buisnesslogic.Repositories.UserRepository;
import buisnesslogic.email.SignUpMessage;
import buisnesslogic.model.Dto.CourseFilterDto;
import buisnesslogic.model.Dto.Response;
import buisnesslogic.Repositories.CategoryRepository;
import buisnesslogic.Repositories.CourseRepository;
import buisnesslogic.Repositories.StudentRepository;
import buisnesslogic.Services.CourseService;
import buisnesslogic.email.MailSender;
import buisnesslogic.model.entity.Category;
import buisnesslogic.model.entity.Course;
import buisnesslogic.model.entity.Student;
import buisnesslogic.exceptions.AlreadyEntrolledException;
import buisnesslogic.exceptions.NoSuchCourseException;
import buisnesslogic.exceptions.SendMessageExeption;
import buisnesslogic.model.entity.User;
import buisnesslogic.model.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
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


    @Autowired
    UserRepository userRepository;


    @Override
    @Transactional
    public Response addStudentToCourse(int courseId) throws NoSuchCourseException, SendMessageExeption, AlreadyEntrolledException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());
        Student student = studentRepository.findByName(user.getUsername());

        try {

            Course course = courseRepository.findById(courseId).get();
            if (course.getStudents().contains(student)) throw new AlreadyEntrolledException();
            else {
                course.getStudents().add(student);
                courseRepository.save(course);

                SignUpMessage signUpMessage = new SignUpMessage(student.getEmail(), course);

                MailSender.makeSend(signUpMessage);
            }
            return new Response("user has been entrolled to course" + course.getTitle());

        }catch (NoSuchElementException exception){
            throw new NoSuchCourseException(courseId);
        }

    }


}
