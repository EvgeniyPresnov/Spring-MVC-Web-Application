package ru.home.springbootcrud.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public String handlerBookNotFoundException(BookNotFoundException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "errorPage";
    }
}
