package springproject.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import springproject.exeptions.BookNotFoundException;
import springproject.model.Book;

import java.util.List;

/**
 * This class performs CRUD operation an a relation database using JdbcTemplate class,
 * which is internally based on the JDBC API.
 *
 *
 * @author Evgeniy Presnov
 */
@Repository
public class BookDAOImpl implements BookDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * The methods returns the list of books using SQL select query
     *
     * @return the list of books
     */
    @Override
    public List<Book> allBooks() {
        return jdbcTemplate.query("SELECT * FROM book"
                , new BookMapper()
        );
    }

    /**
     * The method return the book by id using SQL select query
     *
     * @param id
     * @return Book
     */
    @Override
    public Book getBookId(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?"
                , new Object[]{id}
                , new BookMapper()
        ).stream().findAny().orElse(null);
    }

    /**
     * The method return book by author's name using SQL select query
     *
     * @param author
     * @return Book
     */
    @Override
    public Book getBookByAuthor(String author) {
        return jdbcTemplate.query("SELECT * FROM book WHERE author=?"
                , new Object[]{author}
                , new BookMapper()
        ).stream().findAny().orElse(null);
    }

    /**
     * The method updates data about book by id using SQL update query
     *
     * @param id
     * @param book
     */
    @Override
    public void updateBook(int id, Book book) {
        jdbcTemplate.update("UPDATE book SET title=?, author=?, price=? WHERE id=?",
                book.getTitle(),book.getAuthor(), book.getPrice(), id);
    }

    /**
     * The method of deleting book by id using SQL delete query
     *
     * @param id
     */
    @Override
    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

    /**
     * The method adds a new book to data base using SQL insert query
     *
     * @param book
     */
    @Override
    public void saveBook(Book book) {
        jdbcTemplate.update("INSERT INTO book(title, author, price) VALUES(?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getPrice());
    }

    /**
     * This method checks existing of book by id in data base and
     * throw a customer exception if it doesn't exist
     *
     * @param id
     * @return true or false
     * @throws BookNotFoundException if book doesn't exist in data base
     */
    public boolean checkExistBookById(int id) throws BookNotFoundException {
        Book searchBook = getBookId(id);
        String searchBookByAuthor = searchBook.getAuthor();
        for (Book authors: allBooks()) {
            if (searchBookByAuthor.equals(authors.getAuthor())) {
                return true;
            }
        }
        return false;
    }
}
