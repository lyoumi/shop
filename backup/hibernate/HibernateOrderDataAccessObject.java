package edu.karazin.shop.dao.hibernate;

//@Repository
public class HibernateOrderDataAccessObject{

    /*@Override
    public OrderList getOrderById(int id) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = "from OrderList o where o.orderid = " + id;
            Query query = session.createQuery(hql);
            OrderList order = (OrderList) query.uniqueResult();
            transaction.commit();
            return order;
        } finally {
            session.close();
        }
    }

    @Override
    public OrderList createOrder(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            OrderList order = new OrderList();
            order.setUserid(user.getId());
            session.save(order);
            transaction.commit();
            return order;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean completeOrder(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            OrderList order = getOrderById(id);
            session.delete(order);
            transaction.commit();
            return true;
        } finally {
            session.close();
        }
    }

    @Override
    public List<OrderList> getAllOrders() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            return session.createQuery("from OrderList o").list();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public List<BookList> getOrderBooks(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            return session.createQuery("select o.books from OrderList o where o.orderid =" + id).list();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public OrderList addBookToOrder(User user, int orderId, BookList book, int quantity){

        OrderList order = getOrderById(orderId);
        if (order == null) order = createOrder(user);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            List<BookList> books = order.getBookLists();
            if (books == null) books = new ArrayList<>();
            books.add(book);

            List<OrderDetails> orderDetailsList = order.getOrderDetails();
            if (orderDetailsList == null) orderDetailsList = new ArrayList<>();

            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setBookid(book.getId());
            orderDetails.setOrderid(orderId);
            orderDetails.setQuantity(quantity);
            orderDetailsList.add(orderDetails);

            order.setBookLists(books);
            order.setOrderDetails(orderDetailsList);

            session.update(order);

            transaction.commit();
            session.close();

            return order;
        } finally {
            transaction.commit();
            session.close();
        }
    }*/

}
