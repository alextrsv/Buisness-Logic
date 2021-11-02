package buisnesslogic.Services;

import buisnesslogic.model.Dto.Response;
import buisnesslogic.model.entity.Student;

import java.util.List;

public interface StudentService {
    void delete(int id);
    Student getById(int id);
    Student edit(Student student);
    Student getByEmail(String email);
    List<Student> getAll();
    Response addNewStudent(Student newStudent);
}
