package buisnesslogic.controllers.advices;


import buisnesslogic.model.Dto.Response;
import buisnesslogic.exceptions.AlreadyEntrolledException;
import buisnesslogic.exceptions.NoSuchCourseException;
import buisnesslogic.exceptions.SendMessageExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;


@ControllerAdvice
public class CommonAdvice {


    @ExceptionHandler(SendMessageExeption.class)
    public ResponseEntity<Response> handleMessengerAlreadyOwnedException(SendMessageExeption e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AlreadyEntrolledException.class)
    public ResponseEntity<Response> handleAlreadyEntrolledException(AlreadyEntrolledException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchCourseException.class)
    public ResponseEntity<Response> handleNoSuchElementException(NoSuchElementException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
