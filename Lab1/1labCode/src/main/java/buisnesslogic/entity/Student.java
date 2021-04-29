package buisnesslogic.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stud_id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;


    @ManyToMany(mappedBy = "students")
    private Collection<Course> courses;

    public void addCourse(Course course){
        courses.add(course);
    }

    public int getStud_id() {
        return stud_id;
    }

    public void setStud_id(int stud_id) {
        this.stud_id = stud_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }
}
