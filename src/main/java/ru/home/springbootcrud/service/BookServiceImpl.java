package ru.home.springbootcrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.home.springbootcrud.entity.Book;
import ru.home.springbootcrud.exception.BookNotFoundException;
import ru.home.springbootcrud.repository.BookRepository;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    public Book getBookId(int id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public void deleteBookById(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void updateBookById(int id, Book book) {
        Book updateBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(book));
        updateBook.setId(book.getId());
        updateBook.setAuthor(book.getAuthor());
        updateBook.setTitle(book.getTitle());
        updateBook.setPrice(book.getPrice());
        bookRepository.save(updateBook);
    }

    @Override
    public boolean checkExistBook(int id) {
        Book searchBook = getBookId(id);
        String searchBookByAuthor = searchBook.getAuthor();
        for (Book authors: getBooks()) {
            if (searchBookByAuthor.equals(authors.getAuthor())) {
                return true;
            }
        }
        return false;
    }
}
