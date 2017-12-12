package edu.karazin.shop.repository;

import edu.karazin.shop.model.BookList;
import edu.karazin.shop.repository.util.BaseBookRepositoryTest;
import edu.karazin.shop.repository.util.BaseUserRepositoryTest;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class BookRepositoryTest extends BaseBookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void shouldAddBook(){
        BookList book = getBook(1L);

        BookList save = bookRepository.save(book);

        BookList actual = bookRepository.findOne(save.getId());

        assertEquals(save, actual);
    }

    @Test
    public void shouldUpdateBook(){
        BookList book1 = getBook(1L);

        BookList save1 = bookRepository.save(book1);

        BookList save = bookRepository.findOne(save1.getId());

        save1.setName("Test2");

        BookList save2 = bookRepository.save(save1);

        BookList actual = bookRepository.findOne(save2.getId());

        assertEquals(actual.getName(), "Test2");
        assertEquals(save.getId(), actual.getId());
    }

    @Test
    public void shouldDeleteBook(){
        BookList book = getBook(1L);

        BookList save = bookRepository.save(book);

        BookList actual = bookRepository.findOne(save.getId());

        assertEquals(save, actual);

        bookRepository.delete(actual);

        BookList deleted = bookRepository.findOne(1L);
        assertNull(deleted);
    }

    @Test
    public void shouldFindAll(){
        BookList book1 = getBook(1L);
        BookList book2 = getBook(2L);

        bookRepository.save(book1);
        bookRepository.save(book2);

        List<BookList> books = (List<BookList>) bookRepository.findAll();
        assertEquals(2, books.size());
    }

    @Test
    public void shouldFindAllByDisabledBook(){
        BookList book1 = getBook(1L);
        book1.setDisabledBook(false);
        BookList book2 = getBook(2L);
        book2.setDisabledBook(true);

        bookRepository.save(book1);
        bookRepository.save(book2);

        List<BookList> disabledBooks = bookRepository.findAllByDisabledBook(true);
        List<BookList> notDisabledBooks = bookRepository.findAllByDisabledBook(false);

        assertEquals(1, disabledBooks.size());
        assertEquals(1, notDisabledBooks.size());
    }
}
