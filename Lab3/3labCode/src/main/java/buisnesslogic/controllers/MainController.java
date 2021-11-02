package buisnesslogic.controllers;

//import buisnesslogic.Repositories.UserRepository;
import buisnesslogic.Repositories.UserRepository;
import buisnesslogic.model.Dto.CourseFilterDto;
import buisnesslogic.model.Dto.Response;
import buisnesslogic.Services.CategoryService;
import buisnesslogic.Services.CourseService;
import buisnesslogic.Services.StudentService;
import buisnesslogic.model.entity.Category;
import buisnesslogic.model.entity.Course;
import buisnesslogic.model.entity.Student;
import buisnesslogic.exceptions.AlreadyEntrolledException;
import buisnesslogic.exceptions.SendMessageExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

@RestController
public class MainController {

    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;

    @Autowired
    CategoryService categoryService;


    @GetMapping("/courses")
    private Iterable<Course> getAllCourses(){
        return courseService.getAll();
    }



    @GetMapping(value = "/areas/{areaId}/courses")
    private List<Course> getCoursesByArea(@PathVariable(value = "areaId") int areaId){
        return courseService.getByArea(areaId);
    }


    @GetMapping("/areas/categories/{catId}/courses")
    private Collection<Course> getCoursesByCategoty(@PathVariable(value = "catId") int catId){
       return categoryService.getById(catId).getCourses();
    }


    @PostMapping("/course/filtered")
    private List<Course> getFilteredCourses(@RequestBody CourseFilterDto courseFilterDto) {
        return courseService.getFilteredCourses(courseFilterDto);
    }

    @Autowired
    Environment env;

    @GetMapping("/areas")
    private TreeSet<String> getAllAreas(){
        return categoryService.getAllAsString();
    }
    

    @PostMapping("/areas/categories")
    private List<String> getAllCatByArea(@RequestBody Category categoryDto){
     return categoryService.getCategoriesByArea(categoryDto);
    }


    @PostMapping("/cours/{id}/students")
    private Response addStudentToCourse(@PathVariable("id") int courseId) throws SendMessageExeption, AlreadyEntrolledException {
        return courseService.addStudentToCourse(courseId);
    }


    @GetMapping("/students")
    private List<Student> getAllStudents(){
        return studentService.getAll();
    }


    @PostMapping("/students")
    private Response addNewStudent(@RequestBody Student newStudent){
        return studentService.addNewStudent(newStudent);
    }

}

