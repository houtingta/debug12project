package com.ascendingdc.training.project.repository;

import com.ascendingdc.training.project.model.Customers;
import com.ascendingdc.training.project.model.Orders;
import com.ascendingdc.training.project.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrdersDaoImpl implements OrdersDao {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Orders insert(Orders orders) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.save(orders);
            transaction.commit();
            session.close();
            return orders;
        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("failure to insert record", e);
            session.close();
            return null;
        }
    }

    @Override
    public Orders update(Orders orders) {
        Transaction transaction =null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.update(orders);
            transaction.commit();
            session.close();
            return orders;
        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("failure to update the record");
            session.close();
            return null;
        }
    }

    @Override
    public boolean deleteOrder(long custId, long airId) {
        String hql = "DELETE Orders AS ord WHERE ord.customerId = :custID AND ord.airlineId = :airID";
        int deletedCount = 0;
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            Query<Customers> query = session.createQuery(hql);
            query.setParameter("custID", custId);
            query.setParameter("airID", airId);
            deletedCount = query.executeUpdate();
            transaction.commit();
            session.close();
            return deletedCount >= 1 ? true : false;
        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("unable to delete record", e);
            session.close();
            return false;
        }
    }

    @Override
    public boolean deleteOrder(Orders orders) {
        String hql = "DELETE Orders As ord WHERE ord.id = :Id";
        int deletedCount = 0;
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            Query<Orders> query = session.createQuery(hql);
            query.setParameter("Id", orders.getId());
            deletedCount = query.executeUpdate();
            transaction.commit();
            session.close();
            return deletedCount >= 1 ? true : false;
        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("unable to delete record", e);
            session.close();
            return false;
        }

    }

    @Override
    public List<Orders> getOrders() {
        List<Orders> orders = new ArrayList<>();
        String hql = "FROM Orders";
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Query<Orders> query = session.createQuery(hql);
            orders = query.list();
            session.close();
            return orders;
        }
        catch (HibernateException e) {
            logger.error("failure to retrieve data record", e);
            session.close();
            return orders;
        }
    }

    @Override
    public Orders getOrdersById(long custId, long airId) {
        Orders orders = new Orders();
        String hql = "FROM Orders AS ord WHERE ord.customerId = :custID AND ord.airlineId = :airID";
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Query<Orders> query = session.createQuery(hql);
            query.setParameter("custID", custId);
            query.setParameter("airID", airId);
            orders = query.uniqueResult();
            return orders;
        }
        catch (HibernateException e) {
            logger.error("failure to retrieve data record", e);
            session.close();
            return orders;
        }
    }
}
