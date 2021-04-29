package buisnesslogic.exceptions;

import java.util.NoSuchElementException;

public class NoSuchCourseException extends NoSuchElementException {
    public NoSuchCourseException(int courseId){
        super("course with '" + courseId + "' id does not exist");
    }
}
