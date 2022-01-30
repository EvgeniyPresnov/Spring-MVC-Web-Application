package ru.home.springbootcrud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.home.springbootcrud.entity.Book;
import ru.home.springbootcrud.exception.BookNotFoundException;
import ru.home.springbootcrud.service.BookService;
import ru.home.springbootcrud.utils.BookValidator;

@Controller
@RequestMapping("/books")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookValidator bookValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(bookValidator);
    }

    @GetMapping("/list")
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.getBooks());
        log.info("The list of the books\n");
        return "books";
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable("id") int id, Model model) {
        checkExistBook(id, model);
        return "book";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.getBookId(id));
        log.info("The book before updating: " + bookService.getBookId(id) + "\n");
        return "editBook";
    }

    @PostMapping("/{id}")
    public String updateBook(@PathVariable("id") int id,
                             @ModelAttribute("book") @Validated Book book,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("The data on the form doesn't valid \n");
            return "editBook";
        }
        bookService.updateBookById(id, book);
        log.info("The book after updating: " + book + "\n");
        return "redirect:/books/list";
    }

    @GetMapping("/add")
    public String addBook(@ModelAttribute("book") Book book) {
        return "newBook";
    }

    @PostMapping("/add")
    public String addNewBook(@ModelAttribute("book")
                             @Validated Book book,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("The data on the form doesn't valid\n");
            return "newBook";
        }
        bookService.saveBook(book);
        log.info("Adding a new book: " + book + "\n");
        return "redirect:/books/list";
    }


    @GetMapping("/delete/{id}")
    public String deleteBookById(@PathVariable("id") int id, Model model) {
        log.info("The book will be deleted: " +  bookService.getBookId(id) + "\n");
        checkExistBook(id, model);
        bookService.deleteBookById(id);
        log.info("The book was deleted\n");
        return "redirect:/books/list";
    }

    private void checkExistBook(@PathVariable("id") int id, Model model) {
        try {
            if (bookService.checkExistBook(id)) {
                model.addAttribute("book", bookService.getBookId(id));
                log.info("Getting the book: " + bookService.getBookId(id) + " by id = " + id + "\n");
            }
        } catch (Exception e) {
            log.error("This book doesn't exist by book's id = " + id + "\n");
            throw new BookNotFoundException(bookService.getBookId(id));
        }
    }
}
