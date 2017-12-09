package edu.karazin.shop.service;

import edu.karazin.shop.model.Author;
import edu.karazin.shop.model.BookList;
import edu.karazin.shop.model.Genre;
import edu.karazin.shop.repository.AuthorRepository;
import edu.karazin.shop.repository.BookRepository;
import edu.karazin.shop.repository.GenreRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookStoreServiceTest {

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private GenreRepository genreRepository;

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    BookStoreService bookStoreService;

    @Test
    public void shouldInsertAuthor(){
        when(authorRepository.getAuthorByName(anyString())).thenReturn(new Author());
        when(authorRepository.save((Author) anyObject())).thenReturn(new Author());

        Author author = new Author();
        author.setId(1);
        author.setName("Test");
        author.setBookLists(new ArrayList<>());

        Author save = bookStoreService.insertAuthor(author);

        assertNotNull(save);
    }

    @Test
    public void shouldInsertGenre(){
        when(genreRepository.getGenreByGenrename(anyString())).thenReturn(new Genre());
        when(genreRepository.save((Genre) anyObject())).thenReturn(new Genre());

        Genre genre = new Genre();
        genre.setGenreid(1);
        genre.setGenrename("Test");
        genre.setBookLists(new ArrayList<>());

        Genre save = bookStoreService.insertGenre(genre);

        assertNotNull(save);
    }

    @Test
    public void shouldInsertBook(){
        when(bookRepository.save((BookList) anyObject())).thenReturn(new BookList());

        BookList book = bookStoreService.insertBook(new BookList());

        assertNotNull(book);
    }

    @Test
    public void shouldGetBookListByGenre(){
        Genre genre = new Genre();
        genre.setGenreid(1);
        genre.setGenrename("Test");
        genre.setBookLists(new ArrayList<>());

        when(genreRepository.getGenreByGenrename(anyString())).thenReturn(genre);

        List<BookList> books = bookStoreService.getBookListByGenre("Test");

        assertNotNull(books);
    }

    @Test
    public void shouldGetBookById(){
        when(bookRepository.findOne(anyLong())).thenReturn(new BookList());

        BookList book = bookStoreService.getBookById(1L);

        assertNotNull(book);
    }

    @Test
    public void shouldUpdateBook(){
        when(bookRepository.save((BookList) anyObject())).thenReturn(new BookList());

        BookList book = bookStoreService.updateBook(new BookList());

        assertNotNull(book);
    }

    @Test
    public void shouldDeleteBook(){
        when(bookRepository.findOne(0L)).thenReturn(new BookList());

        bookStoreService.deleteBook(0L);

        verify(bookRepository, times(1)).delete(0L);
    }

    @Test
    public void shouldGetGenreNames(){
        when(genreRepository.findAll()).thenReturn(new ArrayList<>());

        List<String> genres = bookStoreService.getGenreNames();
        List<String> genresByName = bookStoreService.getGenreNames("Test");

        assertNotNull(genres);
        assertNotNull(genresByName);
    }
}
