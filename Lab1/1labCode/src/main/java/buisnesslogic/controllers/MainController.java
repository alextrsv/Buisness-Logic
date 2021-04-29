package buisnesslogic.controllers;

import buisnesslogic.Dto.CourseFilterDto;
import buisnesslogic.Dto.Response;
import buisnesslogic.Services.CategoryService;
import buisnesslogic.Services.CourseService;
import buisnesslogic.Services.StudentService;
import buisnesslogic.entity.Category;
import buisnesslogic.entity.Course;
import buisnesslogic.entity.Student;
import buisnesslogic.exceptions.AlreadyEntrolledException;
import buisnesslogic.exceptions.SendMessageExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    @ResponseStatus(HttpStatus.FORBIDDEN)
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


    @GetMapping("/areas")
    private TreeSet<String> getAllAreas(){
        return categoryService.getAllAsString();
    }


    @PostMapping("/areas/categories")
    private List<String> getAllCatByArea(@RequestBody Category categoryDto){
     return categoryService.getCategoriesByArea(categoryDto);
    }


    @PostMapping("/cours/{id}/students")
    private Response addStudentToCourse(@RequestBody Student newStudent, @PathVariable("id") int courseId) throws SendMessageExeption, AlreadyEntrolledException {
        return courseService.addStudentToCourse(newStudent, courseId);
    }
}

