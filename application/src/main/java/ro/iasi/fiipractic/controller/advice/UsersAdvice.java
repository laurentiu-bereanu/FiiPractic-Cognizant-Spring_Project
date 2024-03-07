package ro.iasi.fiipractic.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.iasi.fiipractic.exception.user.UserNotFoundException;
import ro.iasi.fiipractic.exception.user.UsernameNotUniqueException;

@ControllerAdvice
public class UsersAdvice {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFoundException(UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UsernameNotUniqueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUsernameNotUniqueException(UsernameNotUniqueException ex) {
        return ex.getMessage();
    }
}
