package springproject.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springproject.exeptions.BookNotFoundException;
import springproject.model.Book;
import springproject.services.BookService;
import springproject.utils.BookValidator;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * This class handles the requests which URL ends with "/books"
 *
 * @author Presnov Evgeniy
 */
@Slf4j
@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookValidator bookValidator;

    @PostConstruct
    private void startLogging() {
        log.info("--- Logging BookController is starting ---" + "\n");
    }

    /**
     * The method adds a custom validator for checking of fullness the fields on the form
     *
     * @param binder
     */
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(bookValidator);
    }

    /**
     * The method handles the GET request which URL ends with "/books/list"
     *
     * @param model
     * @return a form that display the list of books in data base
     */
    @GetMapping("/list")
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.allBooks());
        log.info("The list of the books'" + "\n");
        return "showBooks";
    }

    /**
     *  The method handles the GET request which URL ends with "/books/id"
     *
     * @param id
     * @param model
     * @return a form that displays info about the book
     */
    @GetMapping("/{id}")
    public String getBookById(@PathVariable("id") int id, Model model) {
        try {
            if (bookService.checkExistBook(id)) {
                model.addAttribute("book", bookService.getBookId(id));
                log.info("Getting the book by id = " + id + "\n");
            }
        } catch (Exception e) {
            log.error("This book does not exist by book's id = " + id + "\n");
            throw new BookNotFoundException("This book does not exist by id = " + id);
        }
        return "showBook";
    }

    /**
     * The method handles the GET request which URL ends with "/books/edit/id"
     *
     * @param model
     * @param id
     * @return a form that displays info about the book for editing
     */
    @GetMapping("/edit/{id}")
    public String editBook(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.getBookId(id));
        log.info("Ediding info about the book by id = " + id + "\n");
        return "editBook";
    }

    /**
     * This method invokes during editing the fields on the form
     *
     * @param book
     * @param bindingResult
     * @param id
     * @return  a form that displays the books from data base after adding editing the book
     */
    @PostMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Validated Book book, BindingResult bindingResult,
                               @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            log.error("The data from the from don't valid by id = " + id + "\n");
            return "editBook";
        }
        bookService.updateBook(id, book);
        log.info("Updating info about the book by id = " + id + "\n");
        return "redirect:/books/list";
    }

    /**
     * The method handles the GET request which URL ends with "/books/delete/id"
     *
     * @param id
     * @return a form that displays the books from data base after deleting the book
     */
    @GetMapping("delete/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookService.deleteBook(id);
        log.info("Deleting info about the book by id = " + id + "\n");
        return "redirect:/books/list";
    }

    /**
     * The method handles the GET request which URL ends with "/books/add"
     *
     * @param book
     * @return a form for filling info about a new book
     */
    @GetMapping("/add")
    public String newAuthor(@ModelAttribute("book") Book book) {
        return "newBook";
    }

    /**
     * This method invokes during filling info about a new book on the form
     *
     * @param book
     * @param bindingResult
     * @return a form that displays the books from data base after adding a new book
     */
    @PostMapping()
    public String addAuthor(@ModelAttribute("book") @Validated Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("The data from the from don't valid" + "\n");
            return "newBook";
        }
        bookService.saveBook(book);
        log.info("Adding a new book" + "\n");
        return "redirect:/books/list";
    }

    @PreDestroy
    private void stopLogging() {
        log.info("--- Logging BookController is stopped ---" + "\n");
    }

}
