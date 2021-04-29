package buisnesslogic.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class SendMessageExeption extends Exception{
    String message;
    public SendMessageExeption(){
        super();
    }
    public SendMessageExeption(String message){
        super("sending email trouble\nUser has not been entrolled to course");
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
