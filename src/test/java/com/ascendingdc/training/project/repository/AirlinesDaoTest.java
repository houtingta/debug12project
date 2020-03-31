package com.ascendingdc.training.project.repository;

import com.ascendingdc.training.project.model.Airlines;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AirlinesDaoTest {
    private AirlinesDao airlinesDao;
    private Airlines a1;
    private String airlineName = "OP";
    private String tailNumber = "N17452";
    private String testUpdateAirlineName = "PK";
    private String testUpdateTailNumber = "NN5566";
    private String testGetTailNumber = "N783AA";
    private String testDeleteAirlineName = "CD";
    private String testDeleteTailNumber = "NN8579";


    @Before
    public void init() {
        airlinesDao = new AirlinesDaoImpl();
        a1 = new Airlines();
        a1.setName(airlineName);
        a1.setTailNumber(tailNumber);
        a1 = airlinesDao.insert(a1);
    }

    @After
    public void tearDown() { airlinesDao.deleteAirline(a1); }

    @Test
    public void updateAirlineTest() {
        a1.setName(testUpdateAirlineName);
        a1.setTailNumber(testUpdateTailNumber);
        a1 = airlinesDao.update(a1);
        System.out.println(airlinesDao.getAirlinesByTailNum(testUpdateTailNumber));

        a1.setName(airlineName);
        a1.setTailNumber(tailNumber);
        a1 = airlinesDao.update(a1);
        System.out.println(airlinesDao.getAirlinesByTailNum(tailNumber));
    }

    @Test
    public void deleteAirlineByTailNumberTest() {
        Airlines airlines = new Airlines();
        airlines.setName(testDeleteAirlineName);
        airlines.setTailNumber(testDeleteTailNumber);

        airlinesDao.insert(airlines);
        int beforeDelete = airlinesDao.getAirlines().size();

        airlinesDao.deleteAirline(testDeleteTailNumber);
        Assert.assertEquals(beforeDelete, airlinesDao.getAirlines().size());
    }

    @Test
    public void getAirlinesTest() {
        List<Airlines> airlines = airlinesDao.getAirlines();
        int expectedNumOfAir = 20;

        airlines.forEach(airline -> System.out.println(airline));
        Assert.assertEquals(expectedNumOfAir, airlines.size());
    }

    @Test
    public void getAirlineByTailNum() {
        Airlines airlines = airlinesDao.getAirlinesByTailNum(testGetTailNumber);
        System.out.println(airlines);
    }
}
