package com.ascendingdc.training.project.repository;

import com.ascendingdc.training.project.model.Airlines;
import com.ascendingdc.training.project.model.Customers;
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
public class AirlinesDaoImpl implements AirlinesDao{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Airlines insert(Airlines airlines) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.save(airlines);
            transaction.commit();
            session.close();
            return airlines;
        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("failure to insert record", e);
            session.close();
            return null;
        }
    }

    @Override
    public Airlines update(Airlines airlines) {
        Transaction transaction =null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.update(airlines);
            transaction.commit();
            session.close();
            return airlines;
        }
        catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("failure to update the record");
            session.close();
            return null;
        }
    }

    @Override
    public boolean deleteAirline(String tailNumber) {
        String hql = "DELETE Airlines AS air WHERE air.tailNumber = :tailNumber";
        int deletedCount = 0;
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            Query<Customers> query = session.createQuery(hql);
            query.setParameter("tailNumber", tailNumber);
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
    public boolean deleteAirline(Airlines airlines) {
        String hql = "DELETE Airlines AS air WHERE air.id =:Id";
        int deletedCount = 0;
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try{
            transaction = session.beginTransaction();
            Query<Airlines> query = session.createQuery(hql);
            query.setParameter("Id", airlines.getId());
            deletedCount =  query.executeUpdate();
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
    public List<Airlines> getAirlines() {
        List<Airlines> airlines = new ArrayList<>();
        String hql = "FROM Airlines";
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Query<Airlines> query = session.createQuery(hql);
            airlines = query.list();
            session.close();
            return airlines;
        }
        catch (HibernateException e) {
            logger.error("failure to retrieve data record", e);
            session.close();
            return airlines;
        }

    }

    @Override
    public Airlines getAirlinesByTailNum(String tailNumber) {
        Airlines airlines = new Airlines();
        String hql = "FROM Airlines AS air WHERE air.tailNumber = :tailNumber";
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Query<Airlines> query = session.createQuery(hql);
            query.setParameter("tailNumber", tailNumber);
            airlines = query.uniqueResult();
            return airlines;
        }
        catch (HibernateException e) {
            logger.error("failure to retrieve data record", e);
            session.close();
            return airlines;
        }
    }
}
