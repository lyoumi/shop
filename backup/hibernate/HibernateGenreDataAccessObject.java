package edu.karazin.shop.dao.hibernate;

import edu.karazin.shop.model.Genre;
import edu.karazin.shop.dao.GenreDataAccessObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import edu.karazin.shop.util.SessionFactoryImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

//@Repository
public class HibernateGenreDataAccessObject{

    /*private SessionFactory sessionFactory = SessionFactoryImpl.getSessionFactory();

    public HibernateGenreDataAccessObject() {
    }

    @Override
    public Genre getGenreByName(String genrename) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = "select g from Genre g where g.genrename = '" + genrename + "'";
            Query query = session.createQuery(hql);
            Genre genre = (Genre) query.uniqueResult();
            transaction.commit();
            return genre;
        } finally {
            session.close();
        }
    }

    @Override
    public Genre getGenreById(long id) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            String hql = "from Genre g where g.genreid = " + id;
            Query query = session.createQuery(hql);
            return (Genre) query.uniqueResult();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public Genre createGenre(Genre genre) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            String hql = "from Genre g where g.genrename = '" + genre.getGenrename() + "'";
            Query query = session.createQuery(hql);
            return (Genre) query.uniqueResult();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public List<Genre> getAllGenres() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = "from Genre g";
            Query query = session.createQuery(hql);
            return query.getResultList();
        } finally {
            transaction.commit();
            session.close();
        }
    }*/
}
