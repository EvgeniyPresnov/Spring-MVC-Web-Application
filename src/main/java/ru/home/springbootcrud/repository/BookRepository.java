package ru.home.springbootcrud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.home.springbootcrud.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
}
