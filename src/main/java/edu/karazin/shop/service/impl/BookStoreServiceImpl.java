package edu.karazin.shop.service.impl;

import edu.karazin.shop.dao.AuthorDao;
import edu.karazin.shop.dao.BookDao;
import edu.karazin.shop.dao.GenreDao;
import edu.karazin.shop.model.Author;
import edu.karazin.shop.model.BookList;
import edu.karazin.shop.model.Genre;
import edu.karazin.shop.model.Genres;
import edu.karazin.shop.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private BookDao bookDao;

    @Override
    public Author insertAuthor(Author author) {
        Author currentAuthor = authorDao.getAuthorByName(author.getName());
        if (Objects.equals(currentAuthor, null)) {
            author = authorDao.save(author);
            return author;
        } else return currentAuthor;

    }

    @Override
    public Genre insertGenre(Genre genre) {
        Genre currentGenre = genreDao.getGenreByGenrename(genre.getGenrename());
        if (Objects.equals(currentGenre, null)) {
            genre = genreDao.save(genre);
            return genre;
        } else return currentGenre;
    }

    @Override
    public void insertBook(BookList bookList) {
        bookDao.save(bookList);
    }

    @Override
    public List<BookList> getBookListByGenre(String genre) {
        if (genre != null){
            if (!Objects.equals(genre, "")) return genreDao.getGenreByGenrename(genre).getBookLists();
            else return (List<BookList>) bookDao.findAll();
        }
        else return (List<BookList>) bookDao.findAll();
    }

    @Override
    public BookList getBookById(Long id) {
        return bookDao.findOne(id);
    }

    @Override
    public void updateBook(BookList bookList) {
        bookDao.save(bookList);
    }

    @Override
    public void deleteBook(Long id) { bookDao.delete(id);}

    @Override
    public List<String> getGenreNames(String genreName){
        List<Genre> genreList = (List<Genre>) genreDao.findAll();
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
        List<Genre> genreList = (List<Genre>) genreDao.findAll();
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
            Genres[] genreArray = Genres.values();
            for (Genres name :
                    genreArray) {
                Genre genre = new Genre();
                if (name.toString().contains("_")) genre.setGenrename(name.toString().replace("_", " "));
                else genre.setGenrename(name.toString());
                genreList.add(genre);
                genreDao.save(genre);
            }
        }
    }
}
