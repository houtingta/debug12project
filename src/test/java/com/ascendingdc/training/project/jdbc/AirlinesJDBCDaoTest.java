package com.ascendingdc.training.project.jdbc;

import com.ascendingdc.training.project.model.Airlines;
import com.ascendingdc.training.project.model.Customers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AirlinesJDBCDaoTest {
    public AirlinesJDBCDao airlinesJDBCDao;

    @Before
    public void init() { airlinesJDBCDao = new AirlinesJDBCDao(); }

    @Test
    public void insertAirlineTest() {
        List<Airlines> airlines = airlinesJDBCDao.getAirlines();
        System.out.println(airlines.size());
        airlinesJDBCDao.insertAirline("DC", "N10247");
        airlines = airlinesJDBCDao.getAirlines();
        System.out.println(airlines.size());
    }

    @Test
    public void getAirlinesTestAll() {
        List<Airlines> airlines = airlinesJDBCDao.getAirlines();

        //Set the expected number of records
        int expectedNumOfCust = 10;

        //print all records' address string
        for (Airlines airline : airlines) {
            System.out.println(airline);
        }

        //determine the number of records is correct or not
        Assert.assertEquals(expectedNumOfCust, airlines.size());
    }

    @Test
    public void getAirlineTest(){
        Airlines airline = airlinesJDBCDao.getAirlines("N787AA");
        System.out.println(airline);
    }

    @Test
    public void updateAirlineTest(){
        airlinesJDBCDao.updateAirline("VA", "N754AA", (long) 12);
    }


    @Test
    public void deleteAirlineTest() {
        List<Airlines> airlines = airlinesJDBCDao.getAirlines();
        System.out.println(airlines.size());
        airlinesJDBCDao.deleteAirline("N754AA");
        airlines = airlinesJDBCDao.getAirlines();
        System.out.println(airlines.size());
    }

}
