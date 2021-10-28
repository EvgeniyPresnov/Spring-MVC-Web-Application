package springproject.controllers;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import springproject.controllers.categories.UnitTesting;
import springproject.dao.BookDAO;
import springproject.exeptions.BookNotFoundException;
import springproject.model.Book;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@Category(UnitTesting.class)
@RunWith(MockitoJUnitRunner.class)
public class BookControllerUnitTest {

    @Mock
    private BookDAO bookDAO;

    @Test
    public void checkAddBookTest() {
        Book book = new Book(1, "Evgen Presnov", "Qwerty", 123.00);
        bookDAO.saveBook(book);

        verify(bookDAO).saveBook(book);
        verify(bookDAO, times(1)).saveBook(book);
    }

    @Test
    public void checkGetBookByIdTest() {
        Book book1 = new Book(1, "JRR Tolkien", "The Lord of the Rings", 31.43);
        Book book2 = new Book(2, "Jane Austen", "Pride and Prejudice", 12.32);

        when(bookDAO.getBookId(1)).thenReturn(book1);
        when(bookDAO.getBookId(2)).thenReturn(book2);

        assertEquals("JRR Tolkien", bookDAO.getBookId(1).getAuthor());
        assertEquals("Jane Austen", bookDAO.getBookId(2).getAuthor());

        verify(bookDAO, times(2)).getBookId(anyInt());
    }

    @Test
    public void checkGetBookByAuthorNameTest() {
        Book book = new Book(1, "JRR Tolkien", "The Lord of the Rings", 31.43);
        ArgumentCaptor<Book> arg = ArgumentCaptor.forClass(Book.class);

        bookDAO.saveBook(book);
        verify(bookDAO).saveBook(arg.capture());

        assertEquals(book.getAuthor(), arg.getValue().getAuthor());
    }

    @Test(expected = Exception.class)
    public void checkGetBookThatNotExistTest() {
        Book book1 = new Book(1, "JRR Tolkien", "The Lord of the Rings", 31.43);
        Book book2 = new Book(2, "Jane Austen", "Pride and Prejudice", 12.32);
        Book book3 = new Book(3, "ABC", "DFG", 00.00);

        bookDAO.saveBook(book1); bookDAO.saveBook(book2); bookDAO.saveBook(book3);

        when(bookDAO.checkExistBookById(book1.getId())).thenReturn(true);

        assertNotNull(bookDAO.getBookId(book1.getId()).getAuthor());

        when(bookDAO.checkExistBookById(book3.getId())).thenReturn(false);
        when(bookDAO.getBookId(book3.getId())).thenThrow(new BookNotFoundException());

        assertNull(bookDAO.getBookId(book3.getId()).getAuthor());
    }

    @Test
    public void checkDeleteBookByIdTest() {
        Book book1 = new Book(1, "JRR Tolkien", "The Lord of the Rings", 31.43);
        Book book2 = new Book(2, "Jane Austen", "Pride and Prejudice", 12.32);

        bookDAO.saveBook(book1); bookDAO.saveBook(book2);
        bookDAO.deleteBook(book1.getId());

        doNothing().when(bookDAO).deleteBook(book1.getId());
        verify(bookDAO, times(1)).deleteBook(anyInt());
    }

    @Test
    public void checkUpdateBookByIdTest() {
        Book book = new Book(1, "JRR Tolkien", "The Lord of the Rings", 31.43);
        String oldTitle = book.getTitle();
        String newTitle = " The Return of the King";
        book.setTitle(newTitle);

        bookDAO.updateBook(book.getId(), book);

        doNothing().when(bookDAO).updateBook(book.getId(), book);
        verify(bookDAO, times(1)).updateBook(book.getId(), book);

        assertNotEquals(oldTitle, newTitle);
    }

    @Test
    public void checkGetBooksTest() {
        List<Book> books = new ArrayList<>();
        Book book1 = new Book(1, "JRR Tolkien", "The Lord of the Rings", 31.43);
        Book book2 = new Book(2, "Jane Austen", "Pride and Prejudice", 12.32);

        books.add(book1); books.add(book2);
        bookDAO.saveBook(book1); bookDAO.saveBook(book2);

        when(bookDAO.allBooks()).thenReturn(books);

        assertTrue(books.size() > 0);
        assertNotNull(bookDAO.allBooks());
    }
}