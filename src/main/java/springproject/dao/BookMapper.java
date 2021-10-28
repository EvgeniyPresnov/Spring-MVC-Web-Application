package springproject.dao;

import org.springframework.jdbc.core.RowMapper;
import springproject.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class implements RowMapper interface that allows mapping a database record with the instance of a Book class.
 *
 * @author Evgeniy Presnov
 */
public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setAuthor(resultSet.getString("author"));
        book.setTitle(resultSet.getString("title"));
        book.setPrice(resultSet.getDouble("price"));
        return book;
    }
}
