package edu.karazin.shop.dao;

import edu.karazin.shop.model.BookList;
import org.springframework.data.repository.CrudRepository;

public interface BookDao extends CrudRepository<BookList, Long> {
}
