package edu.karazin.shop.repository;

import edu.karazin.shop.model.Genre;
import edu.karazin.shop.repository.util.BaseGenreRepositoryTest;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GenreRepositoryTest extends BaseGenreRepositoryTest{

    @Autowired
    GenreRepository genreRepository;

    @Test
    public void shouldAddGenre(){
        Genre genre = getGenre(1);

        Genre save = genreRepository.save(genre);
        Genre actual = genreRepository.findOne(save.getGenreid());

        assertEquals(save, actual);
    }

    @Test
    public void shouldUpdateGenre(){
        Genre genre = getGenre(1);

        Genre save1 = genreRepository.save(genre);

        Genre save = genreRepository.findOne(save1.getGenreid());

        save1.setGenrename("Test2");

        Genre save2 = genreRepository.save(save1);

        Genre actual = genreRepository.findOne(save2.getGenreid());

        assertEquals("Test2", actual.getGenrename());
        assertEquals(save.getGenreid(), actual.getGenreid());

    }

    @Test
    public void shouldDeleteGenre(){
        Genre genre = getGenre(1);

        Genre save = genreRepository.save(genre);
        Genre actual = genreRepository.findOne(save.getGenreid());

        assertEquals(save, actual);

        genreRepository.delete(actual.getGenreid());
    }

    @Test
    public void shouldFindAll(){
        Genre genre1 = getGenre(1);
        Genre genre2 = getGenre(2);

        genreRepository.save(genre1);
        genreRepository.save(genre2);

        List<Genre> genres = (List<Genre>) genreRepository.findAll();

        assertEquals(2, genres.size());
    }
}
