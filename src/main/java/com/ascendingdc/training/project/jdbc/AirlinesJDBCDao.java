package com.ascendingdc.training.project.jdbc;

import com.ascendingdc.training.project.model.Airlines;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirlinesJDBCDao {

    //Step 1: Put database information
    static final String DBURL = "jdbc:postgresql://localhost:5429/airline";
    static final String USER = "admin";
    static final String PASS = "password";

    //For Insert, Retrieve, Update ,and Delete (CRUD) methods
    //Retrieve Data
    public List<Airlines> getAirlines() {
        List<Airlines> airlines = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            //R-Step 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DBURL, USER, PASS);

            //R-Step 3: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM airlines";
            rs = stmt.executeQuery(sql);

            //R-Step 4: Extract data from result set (rs)
            while(rs.next()) {
                //Retrieve by column name
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String tailNumber = rs.getString("tail_number");

                //Fill the customer object
                Airlines airline = new Airlines();
                airline.setId(id);
                airline.setName(name);
                airline.setTailNumber(tailNumber);
                airlines.add(airline);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            //R-Step 5: finally block used to close resources
            try{
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }

        return airlines;
    }

    // Normal Test: Print out (informal)
    public static void main(String[] args) {
        //Retrieve Test
        AirlinesJDBCDao airlinesJDBCDao = new AirlinesJDBCDao();
        System.out.println(airlinesJDBCDao.getAirlines().size());
    }


}
