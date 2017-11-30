package edu.karazin.shop.dao.hibernate;

import edu.karazin.shop.model.Author;
import edu.karazin.shop.dao.AuthorDataAccessObject;
import edu.karazin.shop.model.Genre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import edu.karazin.shop.util.SessionFactoryImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

//@Repository
public class HibernateAuthorDataAccessObject{
/*
    private SessionFactory sessionFactory = SessionFactoryImpl.getSessionFactory();

    public HibernateAuthorDataAccessObject() {
    }

    @Override
    public Author createAuthor(Author author) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(author);
            transaction.commit();
            return getAuthorByName(author.getName());
        } finally {
            session.close();
        }
    }

    @Override
    public Author getAuthorByName(String name) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            String hql = "from Author a where a.name = '" + name + "'";
            Query query = session.createQuery(hql);
            return (Author) query.uniqueResult();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public Author getAuthorById(long id) {
        return null;
    }*/
}
