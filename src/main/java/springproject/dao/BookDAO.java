package springproject.dao;

import springproject.exeptions.BookNotFoundException;
import springproject.model.Book;

import java.util.List;

/**
 * This class defines DAO operations for the book model.
 *
 * @author Presnov Evgeniy
 */
public interface BookDAO {
    List<Book> allBooks();
    Book getBookId(int id);
    Book getBookByAuthor(String author);
    void updateBook(int id, Book book);
    void deleteBook(int id);
    void saveBook(Book book);
    boolean checkExistBookById(int id) throws BookNotFoundException;
}
