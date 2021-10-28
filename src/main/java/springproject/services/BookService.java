package springproject.services;

import springproject.exeptions.BookNotFoundException;
import springproject.model.Book;

import java.util.List;

/**
 *
 * @author Evgeniy Presnov
 */
public interface BookService {
    List<Book> allBooks();
    Book getBookId(int id);
    Book getBookByAuthor(String author);
    void deleteBook(int id);
    void saveBook(Book book);
    void updateBook(int id, Book book);
    boolean checkExistBook(int id) throws BookNotFoundException;
    boolean checkExistBookByAuthorName(String authorName) throws BookNotFoundException;
}
