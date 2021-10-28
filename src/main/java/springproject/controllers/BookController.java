package springproject.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springproject.exeptions.BookNotFoundException;
import springproject.model.Book;
import springproject.services.BookService;
import springproject.utils.BookValidator;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * This class handles the requests which URL ends with "/books"
 *
 * @author Presnov Evgeniy
 */
@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostConstruct
    private void startLogging() {
        log.info("--- Logging BookController is starting ---" + "\n");
    }

    /**
     * This method gets the books REST API
     *
     * @return the books in data base
     */
    @GetMapping("/list")
    public ResponseEntity<List<Book>> getAllBooks() {
        log.info("The list of the books'" + "\n");
        return new ResponseEntity<>(bookService.allBooks(), HttpStatus.OK);
    }

    /**
     * This method get book by id REST API
     *
     * @param id
     * @return the book by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {
        Book book = null;
        try {
            if (bookService.checkExistBook(id)) {
                log.info("Getting the book by id = " + id + "\n");
                book = bookService.getBookId(id);
            }
        } catch (Exception e) {
            log.error("This book does not exist by book's id = " + id + "\n");
            throw new BookNotFoundException("This book does not exist by this id = " + id);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    /**
     * This method updates the book REST API
     *
     * @param id
     * @param book
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") int id, @RequestBody Book book) {
        try {
            bookService.checkExistBook(id);
        } catch (Exception e) {
            log.error("This book does not exist by book's id = " + id + "\n");
            throw new BookNotFoundException("This book does not exist by this id = " + id);
        }
        bookService.updateBook(id, book);
        log.info("Updating the book by id = " + id + "\n");
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    /**
     * This method deletes the book from data base REST API
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteBook(@PathVariable("id") int id) {
        try {
            bookService.checkExistBook(id);
        } catch (Exception e) {
            log.error("This book does not exist by book's id = " + id + "\n");
            throw new BookNotFoundException("This book does not exist by this id = " + id);
        }
        bookService.deleteBook(id);
        log.info("Deleting the book by id = " + id + "\n");
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    /**
     * This method adds the book in data base REST API
     *
     * @param book - the book will be updating
     * @return
     */
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        bookService.saveBook(book);
        log.info("Adding a new book" + "\n");
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PreDestroy
    private void stopLogging() {
        log.info("--- Logging BookController is stopped ---" + "\n");
    }

}
