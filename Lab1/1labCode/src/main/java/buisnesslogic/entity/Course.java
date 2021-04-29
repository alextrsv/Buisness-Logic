package buisnesslogic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int course_id;

    @Column(name = "title")
    private String title;

    @Column(name = "level")
    private int level;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    Date statDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    Date endDate;

    @Column(name = "platform")
    private String platform;

    @Column(name = "mark")
    private double mark;


//    @ManyToMany(mappedBy = "courses")
//    private Collection<Student> students;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "enrollments",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "stud_id")
    )
    private Collection<Student> students;

    @ManyToMany(mappedBy = "courses")
    private Collection<Category> categories;


    public Course(){};

    public Course(String title, int level, String description,
                  Date statDate, Date endDate, String platform, double mark){
        this.title = title;
        this.level = level;
        this.description = description;
        this.statDate = statDate;
        this.endDate = endDate;
        this.platform = platform;
        this.mark = mark;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    @JsonIgnore
    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    @JsonIgnore
    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStatDate() {
        return statDate;
    }

    public void setStatDate(Date statDate) {
        this.statDate = statDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}
