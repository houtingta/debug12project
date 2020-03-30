package com.ascendingdc.training.project.jdbc;

import com.ascendingdc.training.project.model.Customers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CustomersJDBCDao {

    //Logger Practice:
    private Logger logger = LoggerFactory.getLogger(getClass());

    //Step 1: Put database information
    static final String DBURL = "jdbc:postgresql://localhost:5429/airline";
    static final String USER = "admin";
    static final String PASS = "password";

    //For Insert, Retrieve, Update ,and Delete (CRUD) methods

    //C-Insert Data
    public void insertCustomer(String customerName){
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
            sql = "INSERT INTO customers (name) VALUES (?)";
//            System.out.println(sql);
            //logger practice replace top code
            logger.warn(sql);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, customerName);
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

    //R-Retrieve Data - for all
    public List<Customers> getCustomers() {

        List<Customers> customers = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

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
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM customers";
            System.out.println(sql);
            //logger practice replace top code
            logger.warn(sql);
            rs = stmt.executeQuery(sql);

            //R-Step 4: Extract data from result set (rs)
            while(rs.next()) {
                //Retrieve by column name
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String telephone = rs.getString("telephone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zipcode = rs.getString("zipcode");

                //Fill the customer object
                Customers customer = new Customers();
                customer.setId(id);
                customer.setName(name);
                customer.setFirstName(firstName);
                customer.setLastName(lastName);
                customer.setTelephone(telephone);
                customer.setEmail(email);
                customer.setAddress(address);
                customer.setCity(city);
                customer.setState(state);
                customer.setZipcode(zipcode);
                customers.add(customer);
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

        return customers;
    }

    //R-Retrieve Data for specific condition (name)
    public Customers getCustomers(String customerName) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Customers customer = null;

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
            sql = "SELECT * FROM customers WHERE name = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, customerName);
//          System.out.println(sql);
            //logger practice replace top code
            logger.warn(sql);
            rs = preparedStatement.executeQuery();

            //R-Step 4: Extract data from result set (rs)
            while(rs.next()) {
                //Retrieve by column name
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String telephone = rs.getString("telephone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zipcode = rs.getString("zipcode");

                //Fill the customer object
                customer = new Customers();
                customer.setId(id);
                customer.setName(name);
                customer.setFirstName(firstName);
                customer.setLastName(lastName);
                customer.setTelephone(telephone);
                customer.setEmail(email);
                customer.setAddress(address);
                customer.setCity(city);
                customer.setState(state);
                customer.setZipcode(zipcode);
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

        return customer;
    }

    //U-Update Data
    public void updateCustomer(String CustomerName, Long id){
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
            sql = "UPDATE customers SET name = ? WHERE id = ?";
//            System.out.println(sql);
            //logger practice replace top code
            logger.warn(sql);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, CustomerName);
            preparedStatement.setLong(2, id);
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
    public void deleteCustomer(String customerName){
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
            sql = "DELETE FROM customers WHERE name = (?)";
//            System.out.println(sql);
            //logger practice replace top code
            logger.warn(sql);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, customerName);
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
