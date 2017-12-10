package edu.karazin.shop.repository;

import edu.karazin.shop.model.BookList;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<BookList, Long> {
    List<BookList> findAllByDisabledBook(boolean check);
}
