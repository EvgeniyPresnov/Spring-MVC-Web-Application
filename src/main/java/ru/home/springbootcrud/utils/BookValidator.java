package ru.home.springbootcrud.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.home.springbootcrud.entity.Book;

@Component
public class BookValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"author", "book.author.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"title", "book.title.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"price", "book.price.empty");
    }
}
