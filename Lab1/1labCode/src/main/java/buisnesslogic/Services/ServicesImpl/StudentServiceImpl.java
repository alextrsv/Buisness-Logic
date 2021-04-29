package buisnesslogic.Services.ServicesImpl;

import buisnesslogic.Repositories.StudentRepository;
import buisnesslogic.Services.StudentService;
import buisnesslogic.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student getById(int id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        studentRepository.delete(getById(id));
    }

    @Override
    public Student edit(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getByEmail(String email) {
        return studentRepository.findByEmail(email);
    }
}
