package ru.home.springbootcrud.service;

import ru.home.springbootcrud.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();
    Book getBookId(int id);
    void deleteBookById(int id);
    void saveBook(Book book);
    void updateBookById(int id, Book book);
    boolean checkExistBook(int id);
}
