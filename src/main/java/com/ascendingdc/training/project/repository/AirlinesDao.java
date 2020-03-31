package com.ascendingdc.training.project.repository;

import com.ascendingdc.training.project.model.Airlines;

import java.util.List;

public interface AirlinesDao {
    Airlines insert(Airlines airlines);
    Airlines update(Airlines airlines);
    boolean deleteAirline(String tailNumber);
    boolean deleteAirline(Airlines airlines);
    List<Airlines> getAirlines();
    Airlines getAirlinesByTailNum(String tailNumber);
}
