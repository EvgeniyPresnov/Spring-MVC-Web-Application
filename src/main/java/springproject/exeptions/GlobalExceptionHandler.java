package springproject.exeptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This class presents a global exceptions handler.
 *
 * @author Evgeniy Presnov
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public String handlerBookNotFoundException(BookNotFoundException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "errorPage";
    }
}
