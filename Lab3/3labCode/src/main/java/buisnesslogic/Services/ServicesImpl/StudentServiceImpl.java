package buisnesslogic.Services.ServicesImpl;

import buisnesslogic.Repositories.RoleRepository;
import buisnesslogic.Repositories.StudentRepository;
import buisnesslogic.Repositories.UserRepository;
import buisnesslogic.Services.StudentService;
import buisnesslogic.model.Dto.Response;
import buisnesslogic.model.entity.Student;
import buisnesslogic.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

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

    @Override
    public List<Student> getAll(){ return (List<Student>) studentRepository.findAll(); }


    @Override
    @Transactional
    public Response addNewStudent(Student newStudent) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

        User newUser = new User(newStudent.getName(), encoder.encode(newStudent.getPassword()));
        newUser.setRoles(Set.of(roleRepository.findById(3).get()));
        newStudent.setUser(newUser);

//        userRepository.save(newUser);
        studentRepository.save(newStudent);

        return new Response("user was signed in");
    }
}
