package buisnesslogic.email;


import buisnesslogic.model.entity.Course;

public class SignUpMessage extends UserMessage {
    Course course;


    public SignUpMessage(){
        this.messageText = "Здравствуйте! Вы записаны на курс ";
        this.errorText = "sending email trouble\nUser has not been entrolled to course" + course.toString();
    }

    public SignUpMessage(Course course) {
        this.course = course;
        this.messageText = "Здравствуйте! Вы записаны на курс " + course.toString();
        this.errorText = "sending email trouble\nUser has not been entrolled to course" + course.toString();
    }

    public SignUpMessage(String toAddr, Course course) {
        super(toAddr);
        this.messageText = "Здравствуйте! Вы записаны на курс " + course.toString();
        this.errorText = "sending email trouble\nUser has not been entrolled to course" + course.toString();

    }

    public SignUpMessage(String toAddr, String messageText, Course course, String errorText) {
        super(toAddr, messageText, errorText);
        this.course = course;
        this.messageText = messageText;
        this.errorText = "sending email trouble\nUser has not been entrolled to course" + course.toString();
    }


    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

