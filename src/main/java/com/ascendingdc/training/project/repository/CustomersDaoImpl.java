package com.ascendingdc.training.project.repository;

import com.ascendingdc.training.project.model.Customers;
import com.ascendingdc.training.project.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CustomersDaoImpl implements CustomersDao{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Customers insert(Customers customers) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.save(customers);
            transaction.commit();
            session.close();
            return customers;
        }
        catch(Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("failure to insert record", e);
            session.close();
            return null;
        }
    }

    @Override
    public Customers update(Customers customers) {
        Transaction transaction =null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.update(customers);
            transaction.commit();
            session.close();
            return customers;
        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("failure to update the record");
            session.close();
            return null;
        }
    }

    @Override
    public boolean deleteCustomer(String custName) {
        String hql = "DELETE Customers AS cust WHERE cust.name = :name";
        int deletedCount = 0;
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            Query<Customers> query = session.createQuery(hql);
            query.setParameter("name", custName);
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
    public boolean deleteCustomer(Customers customers) {
        String hql = "DELETE Customers AS cust WHERE cust.id = :Id";
        int deletedCount = 0;
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            Query<Customers> query = session.createQuery(hql);
            query.setParameter("Id", customers.getId());
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
    public List<Customers> getCustomers() {
        List<Customers> customers = new ArrayList<>();
        String hql = "FROM Customers";
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Query<Customers> query = session.createQuery(hql);
            customers = query.list();
            session.close();
            return customers;
        }
        catch (HibernateException e) {
            logger.error("failure to retrieve data record", e);
            session.close();
            return customers;
        }
    }

    @Override
    public Customers getCustomersByName(String custName) {
        Customers customers = new Customers();
        String hql = "FROM Customers AS cust WHERE cust.name = :name";
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Query<Customers> query = session.createQuery(hql);
            query.setParameter("name", custName);
            customers = query.uniqueResult();
            return customers;
        }
        catch (HibernateException e) {
            logger.error("failure to retrieve data record", e);
            session.close();
            return customers;
        }
    }

}
