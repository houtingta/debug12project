package com.ascendingdc.training.project.jdbc;

import com.ascendingdc.training.project.model.Airlines;
import com.ascendingdc.training.project.model.Customers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirlinesJDBCDao {

    //Logger Practice:
    private Logger logger = LoggerFactory.getLogger(getClass());

    //Step 1: Put database information
    static final String DBURL = "jdbc:postgresql://localhost:5429/airline";
    static final String USER = "admin";
    static final String PASS = "password";

    //For Insert, Retrieve, Update ,and Delete (CRUD) methods
    //C-Insert Data
    public void insertAirline(String airlineName, String tailNumber){
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            //C-Step 2: Open a connection
//            System.out.println("Connecting to database...");
            //logger practice replace top code
            logger.debug("Connecting to database...");
            conn = DriverManager.getConnection(DBURL, USER, PASS);

            //C-Step 3: Execute the insert command
//            System.out.println("Creating statement...");
            //logger practice replace top code
            logger.info("Creating statement...");

            String sql;
            sql = "INSERT INTO airlines (name, tail_number) VALUES (?, ?)";
//            System.out.println(sql);
            //logger practice replace top code
            logger.warn(sql);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, airlineName);
            preparedStatement.setString(2, tailNumber);
            System.out.println(preparedStatement);
            preparedStatement.execute();

            System.out.println("Insertion completed");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            //C-Step 5: finally block used to close resources
            try{
                if(conn != null) conn.close();
                if(preparedStatement != null) preparedStatement.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    //Retrieve Data - for all
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

    //R-Retrieve Data for specific condition (name)
    public Airlines getAirlines(String flightNumber) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Airlines airline = null;

        try{
            //R-Step 2: Open a connection
//            System.out.println("Connecting to database...");

            //logger practice replace top code
            logger.debug("Connecting to database...");
            conn = DriverManager.getConnection(DBURL, USER, PASS);

            //R-Step 3: Execute a query
//            System.out.println("Creating statement...");
            //logger practice replace top code
            logger.info("Creating statement...");
            String sql;
            sql = "SELECT * FROM airlines WHERE tail_number = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, flightNumber);
//          System.out.println(sql);
            //logger practice replace top code
            logger.warn(sql);
            rs = preparedStatement.executeQuery();

            //R-Step 4: Extract data from result set (rs)
            while(rs.next()) {
                //Retrieve by column name
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String tailNumber = rs.getString("tail_number");


                //Fill the customer object
                airline = new Airlines();
                airline.setId(id);
                airline.setName(name);
                airline.setTailNumber(tailNumber);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            //R-Step 5: finally block used to close resources
            try{
                if(rs != null) rs.close();
                if(preparedStatement != null) preparedStatement.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }

        return airline;
    }

    //U-Update Data
    public void updateAirline(String airlineName, String tailNumber, Long id){
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            //U-Step 2: Open a connection
//            System.out.println("Connecting to database...");
            //logger practice replace top code
            logger.debug("Connecting to database...");
            conn = DriverManager.getConnection(DBURL, USER, PASS);

            //U-Step 3: Execute the delete command
//            System.out.println("Creating statement...");
            //logger practice replace top code
            logger.info("Creating statement...");

            String sql;
            sql = "UPDATE airlines SET name = ?, tail_number = ? WHERE id = ?";
//            System.out.println(sql);
            //logger practice replace top code
            logger.warn(sql);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, airlineName);
            preparedStatement.setString(2, tailNumber);
            preparedStatement.setLong(3, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

            System.out.println("Update completed");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            //U-Step 4: finally block used to close resources
            try{
                if(conn != null) conn.close();
                if(preparedStatement != null) preparedStatement.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    //D-Delete Data
    public void deleteAirline(String tailNumber){
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            //D-Step 2: Open a connection
//            System.out.println("Connecting to database...");
            //logger practice replace top code
            logger.debug("Connecting to database...");
            conn = DriverManager.getConnection(DBURL, USER, PASS);

            //D-Step 3: Execute the delete command
//            System.out.println("Creating statement...");
            //logger practice replace top code
            logger.info("Creating statement...");

            String sql;
            sql = "DELETE FROM airlines WHERE tail_number = ?";
//            System.out.println(sql);
            //logger practice replace top code
            logger.warn(sql);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, tailNumber);
            System.out.println(preparedStatement);
            preparedStatement.execute();

            System.out.println("Delete completed");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            //D-Step 4: finally block used to close resources
            try{
                if(conn != null) conn.close();
                if(preparedStatement != null) preparedStatement.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

}
