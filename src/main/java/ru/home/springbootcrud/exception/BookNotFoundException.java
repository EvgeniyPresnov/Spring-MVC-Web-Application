package ru.home.springbootcrud.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.home.springbootcrud.entity.Book;

@Getter
@Slf4j
public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Book book) {
        super(String.format("The book titled '%s', published by '%s' doesn't exist",
                book.getTitle(), book.getAuthor()));
    }

    public BookNotFoundException(int id) {
        super(String.format("The book by id = '%s' doesn't exist", id));
    }
}
