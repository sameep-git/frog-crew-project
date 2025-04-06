package edu.tcu.cs.frogcrew.system.exception;

import edu.tcu.cs.frogcrew.crewmember.CrewMemberNotFoundException;
import edu.tcu.cs.frogcrew.system.Result;
import edu.tcu.cs.frogcrew.system.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(CrewMemberNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handleCrewMemberNotFoundException(CrewMemberNotFoundException ex) {
        return new Result(false, StatusCode.NOT_FOUND, ex.getMessage());
    }
}
