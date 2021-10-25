package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCExecutor {
    
    public static void main(String... args) {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                                            "hplussport", "postgres", "password");
        try {
            Connection connection = dcm.getConnection();
            CustomerDAO customerDAO = new CustomerDAO(connection);
            Customer customer = new Customer();
            customer.setFirstName("John");
            customer.setLastName("Doe");
            customer.setEmail("john.doe@wh.gov");
            customer.setPhone("(555) 555-6543");
            customer.setAddress("123 Main Street");
            customer.setCity("DC");
            customer.setState("Washington");
            customer.setZipCode("22121");

            customerDAO.create(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
