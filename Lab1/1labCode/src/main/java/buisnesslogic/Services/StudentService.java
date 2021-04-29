package buisnesslogic.Services;

import buisnesslogic.entity.Student;
import org.springframework.stereotype.Service;

public interface StudentService {
    void delete(int id);
    Student getById(int id);
    Student edit(Student student);
    Student getByEmail(String email);
}
