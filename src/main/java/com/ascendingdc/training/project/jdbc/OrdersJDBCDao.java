package com.ascendingdc.training.project.jdbc;

import com.ascendingdc.training.project.model.Airlines;
import com.ascendingdc.training.project.model.Customers;
import com.ascendingdc.training.project.model.Orders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdersJDBCDao {
    //Logger Practice:
    private Logger logger = LoggerFactory.getLogger(getClass());

    //Step 1: Put database information
    static final String DBURL = "jdbc:postgresql://localhost:5429/airline";
    static final String USER = "admin";
    static final String PASS = "password";

    //For Insert, Retrieve, Update ,and Delete (CRUD) methods

    //C-Insert Data
    public void insertOrder(long customerId, long airlineId){
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
            sql = "INSERT INTO orders (customer_id, airline_id) VALUES (?, ?)";
//            System.out.println(sql);
            //logger practice replace top code
            logger.warn(sql);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, customerId);
            preparedStatement.setLong(2, airlineId);
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
    public List<Orders> getOrders() {
        List<Orders> orders = new ArrayList();
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
            sql = "SELECT * FROM orders";
            rs = stmt.executeQuery(sql);

            //R-Step 4: Extract data from result set (rs)
            while(rs.next()) {
                //Retrieve by column name
                Long id = rs.getLong("id");
                Date orderDate = rs.getDate("order_date");
                Date flightDate = rs.getDate("flight_date");
                String level = rs.getString("level");
                String seat = rs.getString("seat");
                float balance = rs.getFloat("balance");
                long customerId = rs.getLong("customer_id");
                long airlineId = rs.getLong("airline_id");

                //Fill the customer object
                Orders order = new Orders();
                order.setId(id);
                order.setOrderDate((java.sql.Date) orderDate);
                order.setFlightDate((java.sql.Date) flightDate);
                order.setLevel(level);
                order.setSeat(seat);
                order.setBalance(balance);
                order.setCustomerId(customerId);
                order.setAirlineId(airlineId);
                orders.add(order);
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

        return orders;
    }
    //R-Retrieve Data for specific condition (name)
    public Orders getOrder(long customerID, long airlineID) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Orders order = null;

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
            sql = "SELECT * FROM orders WHERE customer_id = ? AND airline_id = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, customerID);
            preparedStatement.setLong(2, airlineID);
//          System.out.println(sql);
            //logger practice replace top code
            logger.warn(sql);
            rs = preparedStatement.executeQuery();

            //R-Step 4: Extract data from result set (rs)
            while(rs.next()) {
                //Retrieve by column name
                Long id = rs.getLong("id");
                Date orderDate = rs.getDate("order_date");
                Date flightDate = rs.getDate("flight_date");
                String level = rs.getString("level");
                String seat = rs.getString("seat");
                float balance = rs.getFloat("balance");
                long customerId = rs.getLong("customer_id");
                long airlineId = rs.getLong("airline_id");


                //Fill the customer object
                order = new Orders();
                order.setId(id);
                order.setOrderDate((java.sql.Date) orderDate);
                order.setFlightDate((java.sql.Date) flightDate);
                order.setLevel(level);
                order.setSeat(seat);
                order.setBalance(balance);
                order.setCustomerId(customerId);
                order.setAirlineId(airlineId);
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

        return order;
    }

    //U-Update Data
    public void updateOrder(Long customerID, Long airlineID, Long id){
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
            sql = "UPDATE orders SET customer_id = ?, airline_id = ? WHERE id = ?";
//            System.out.println(sql);
            //logger practice replace top code
            logger.warn(sql);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, customerID);
            preparedStatement.setLong(2, airlineID);
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
    public void deleteOrder(long customerID, long airlineID) {
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
            sql = "DELETE FROM orders WHERE customer_id = ? AND airline_id = ?";
//            System.out.println(sql);
            //logger practice replace top code
            logger.warn(sql);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, customerID);
            preparedStatement.setLong(2, airlineID);
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
