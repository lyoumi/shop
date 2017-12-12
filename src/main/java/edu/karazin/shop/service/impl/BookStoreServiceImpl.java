package edu.karazin.shop.service.impl;

import edu.karazin.shop.repository.AuthorRepository;
import edu.karazin.shop.repository.BookRepository;
import edu.karazin.shop.repository.GenreRepository;
import edu.karazin.shop.model.Author;
import edu.karazin.shop.model.BookList;
import edu.karazin.shop.model.Genre;
import edu.karazin.shop.model.Genres;
import edu.karazin.shop.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Author insertAuthor(Author author) {
        Author currentAuthor = authorRepository.getAuthorByName(author.getName());
        if (Objects.equals(currentAuthor, null)) {
            author = authorRepository.save(author);
            return author;
        } else return currentAuthor;

    }

    @Override
    public Genre insertGenre(Genre genre) {
        Genre currentGenre = genreRepository.getGenreByGenrename(genre.getGenrename());
        if (Objects.equals(currentGenre, null)) {
            genre = genreRepository.save(genre);
            return genre;
        } else return currentGenre;
    }

    @Override
    public BookList insertBook(BookList bookList) {
        return bookRepository.save(bookList);
    }

    @Override
    public List<BookList> getBookListByGenre(String genre) {
        List<BookList> bookList = bookRepository.findAllByDisabledBook(false);
        if (!StringUtils.isEmpty(genre)) bookList = genreRepository.getGenreByGenrename(genre).getBookLists();
        return bookList;
    }

    @Override
    public BookList getBookById(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public BookList updateBook(BookList bookList) {
        return bookRepository.save(bookList);
    }

    @Override
    public BookList deleteBook(Long id) {
        BookList book = getBookById(id);
        book.setDisabledBook(true);
        return bookRepository.save(book);
    }

    @Override
    public List<String> getGenreNames(String genreName){
        List<Genre> genreList = (List<Genre>) genreRepository.findAll();
        createGenresIfItNotExist(genreList);
        List<String> genres = new ArrayList<>();
        for (Genre genre :
             genreList) {
            if (genre.getGenrename().toLowerCase().contains(genreName.toLowerCase())) genres.add(genre.getGenrename());
        }
        return genres;
    }

    @Override
    public List<String> getGenreNames() {
        List<Genre> genreList = (List<Genre>) genreRepository.findAll();
        createGenresIfItNotExist(genreList);
        List<String> genres = new ArrayList<>();
        for (Genre genre :
                genreList) {
            genres.add(genre.getGenrename());
        }
        return genres;
    }

    private void createGenresIfItNotExist(List<Genre> genreList){
        if (Objects.equals(genreList, null) || genreList.isEmpty()) {
            String[] genreArray = Genres.initGenres.split(",");
            for (String name :
                    genreArray) {
                Genre genre = new Genre();
                if (name.contains("_")) genre.setGenrename(name.replace("_", " "));
                else genre.setGenrename(name);
                genreList.add(genre);
                genreRepository.save(genre);
            }
        }
    }
}
