package springproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springproject.dao.BookDAO;
import springproject.exeptions.BookNotFoundException;
import springproject.model.Book;

import java.util.List;

/**
 * BookService contains all the business logic.
 *
 * @author Evgeniy Presnov
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDAO;

    /**
     * The methods returns the list of books
     *
     * @return the list of books
     */
    @Override
    public List<Book> allBooks() {
        return bookDAO.allBooks();
    }

    /**
     * The method return the book by id
     *
     * @param id
     * @return Book
     */
    @Override
    public Book getBookId(int id) {
        return bookDAO.getBookId(id);
    }

    /**
     * The method return book by author's name
     *
     * @param author
     * @return Book
     */
    @Override
    public Book getBookByAuthor(String author) {
        return bookDAO.getBookByAuthor(author);
    }

    /**
     * The method of deleting book by id
     *
     * @param id
     */
    @Override
    public void deleteBook(int id) {
        bookDAO.deleteBook(id);
    }

    /**
     * The method adds a new book to data base
     *
     * @param book
     */
    @Override
    public void saveBook(Book book) {
        bookDAO.saveBook(book);
    }

    /**
     * The method updates data about book by id
     *
     * @param id
     * @param book
     */
    @Override
    public void updateBook(int id, Book book) {
        bookDAO.updateBook(id, book);
    }

    /**
     * This method checks existing of book by id in data base and
     * throw a customer exception if it doesn't exist
     *
     * @param id
     * @return true or false
     * @throws BookNotFoundException if book doesn't exist in data base
     */
    public boolean checkExistBook(int id) throws BookNotFoundException {
        Book searchBook = getBookId(id);
        String searchBookByAuthor = searchBook.getAuthor();
        for (Book authors: allBooks()) {
            if (searchBookByAuthor.equals(authors.getAuthor())) {
                return true;
            }
        }
        return false;
    }


    /**
     * This method checks existing of book by author's name in data base and
     * throw a customer exception if it doesn't exist
     *
     * @param authorName
     * @return true or false
     * @throws BookNotFoundException if book doesn't exist in data base
     */
    public boolean checkExistBookByAuthorName(String authorName) throws BookNotFoundException {
        Book searchBook = getBookByAuthor(authorName);
        String searchBookByAuthor = searchBook.getAuthor();
        for (Book authors: allBooks()) {
            if (searchBookByAuthor.equals(authors.getAuthor())) {
                return true;
            }
        }
        return false;
    }
}
