package org.example.student;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(NotFoundException.class) @ResponseStatus(HttpStatus.NOT_FOUND)
    Map<String,Object> notFound(NotFoundException e){return Map.of("message",e.getMessage());}
    @ExceptionHandler({DuplicateEmailException.class,DataIntegrityViolationException.class}) @ResponseStatus(HttpStatus.CONFLICT)
    Map<String,Object> duplicate(Exception e){return Map.of("message","A student with this email already exists");}
    @ExceptionHandler(MethodArgumentNotValidException.class) @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String,Object> invalid(MethodArgumentNotValidException e){Map<String,String> errors=new LinkedHashMap<>();e.getBindingResult().getFieldErrors().forEach(f->errors.putIfAbsent(f.getField(),f.getDefaultMessage()));return Map.of("message","Validation failed","errors",errors);}
    @ExceptionHandler(IllegalArgumentException.class) @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String,Object> badRequest(IllegalArgumentException e){return Map.of("message",e.getMessage());}
}
