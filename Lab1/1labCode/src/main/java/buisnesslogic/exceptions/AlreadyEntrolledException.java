package buisnesslogic.exceptions;

import java.util.NoSuchElementException;

public class AlreadyEntrolledException extends Exception {
    public AlreadyEntrolledException(){
        super("пользователь уже записан на курс");
    }
}
