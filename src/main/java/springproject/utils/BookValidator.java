package springproject.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import springproject.model.Book;

/**
 * The custom validator to validate a form for multiple fields.
 *
 * @author Evgeniy Presnov
 */
@Component
public class BookValidator implements Validator {

    /**
     *  This Validator validates Book instances
     *
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors e) {
        ValidationUtils.rejectIfEmptyOrWhitespace(e,"author", "book.author.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(e,"title", "book.title.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(e,"price", "book.price.empty");
    }
}