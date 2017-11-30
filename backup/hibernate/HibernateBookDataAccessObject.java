package edu.karazin.shop.dao.hibernate;

//@Repository
public class HibernateBookDataAccessObject{

    /*private SessionFactory sessionFactory = SessionFactoryImpl.getSessionFactory();

    public HibernateBookDataAccessObject(){}

    @Override
    public boolean addNewBook(BookList book) {
        Session session = null;
        try {
            session = SessionFactoryImpl.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(book);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            session.close();
            return false;
        }
    }

    @Override
    public BookList getBookById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("select b from BookList b where b.id = :id");
            query.setParameter("id", id);
            transaction.commit();
            return (BookList) query.uniqueResult();
        } catch (Exception e){
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean removeById(long id) {
        BookList book = getBookById(id);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(book);
            transaction.commit();
            return true;
        } catch (Exception e){
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(BookList book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(book);
            transaction.commit();
            return true;
        } catch (Exception e){
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<BookList> getAllBooks() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = "from BookList b";
            Query query = session.createQuery(hql);
            return query.getResultList();
        } finally {
            transaction.commit();
            session.close();
        }

    }

    @Override
    public BookList getBookByName(String name){
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String hql = "from BookList b where b.name = '" + name + "'";
            Query query = session.createQuery(hql);
            BookList book = (BookList) query.uniqueResult();
            session.getTransaction().commit();
            return book;
        }
    }*/
}
