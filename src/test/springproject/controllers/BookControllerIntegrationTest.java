package springproject.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import springproject.controllers.categories.IntegrationTesting;
import springproject.model.Book;
import springproject.services.BookService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;

import static org.mockito.Mockito.*;
;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Category(IntegrationTesting.class)
@RunWith(MockitoJUnitRunner.class)
public class BookControllerIntegrationTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(
                bookController
        ).build();
    }

    @Test
    public void getAllBooksTest() throws Exception {
        List<Book> books = new ArrayList<>();
        books.add(new Book());
        books.add(new Book());

        when(bookService.allBooks()).thenReturn(books);

        mockMvc.perform(get("/books/list"))
                .andExpect(status().isOk());

        verify(bookService, times(1)).allBooks();
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void getBookByIdTest() throws Exception {
        Book book = new Book(1, "JRR Tolkien", "The Lord of the Rings", 31.43);
        int id = book.getId();

        when(bookService.getBookId(id)).thenReturn(new Book());

        mockMvc.perform(get("/books/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    public void addBookTest() throws Exception {
        Book book = new Book(1, "JRR Tolkien", "The Lord of the Rings", 31.43);
        int id = book.getId();

        when(bookService.checkExistBook(id)).thenReturn(false);
        doNothing().when(bookService).saveBook(book);

        mockMvc.perform(post("/books/add"));

        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void updateBookTest() throws Exception {
        Book book = new Book(1, "JRR Tolkien", "The Lord of the Rings", 31.43);
        int id = book.getId();

        when(bookService.checkExistBook(id)).thenReturn(true);
        doNothing().when(bookService).updateBook(id, book);

        mockMvc.perform(put("/book/edit/{id}", id));

        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void deleteBookTest() throws Exception {
        Book book = new Book(1, "JRR Tolkien", "The Lord of the Rings", 31.43);
        int id = book.getId();

        doNothing().when(bookService).deleteBook(id);

        mockMvc.perform(delete("/books/delete/{id}", id));

        verifyNoMoreInteractions(bookService);
    }
}
