package code.vanilson.startup.config;

import java.sql.*;

import static java.lang.Class.forName;

public class GDRConnector {
    Connection connection = null;

    public Connection connectionToDB() throws SQLException {

        try {
            // Register the Oracle JDBC driver
            forName("oracle.jdbc.driver.OracleDriver");

            // Provide the connection URL, username, and password
            String url = "jdbc:oracle:thin:@localhost:1521:XE"; // Replace with your Oracle connection URL
            String username = "root"; // Replace with your Oracle username
            String password = "mypass"; // Replace with your Oracle password

            // Create the connection
            connection = DriverManager.getConnection(url, username, password);


            // Test if the connection is successful
            if (connection != null) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Connection failed!");
            }

            // Perform database operations...
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return connection;
    }

    public void selectStatement() throws SQLException {

        if (connectionToDB() != null) {
            try {
                // Create a statement object
                Statement statement = connectionToDB().createStatement();

                // Execute a query
                String query = "SELECT * FROM your_table"; // Replace with your table name
                ResultSet resultSet = statement.executeQuery(query);

                // Process the query results
                while (resultSet.next()) {
                    // Retrieve data from the result set
                    String column1Value = resultSet.getString("column1"); // Replace with your column name
                    int column2Value = resultSet.getInt("column2"); // Replace with your column name

                    // Do something with the retrieved data
                    System.out.println("Column 1 value: " + column1Value);
                    System.out.println("Column 2 value: " + column2Value);
                }

                // Close the result set and statement
                resultSet.close();
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Close the connection
                try {
                    connectionToDB().close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        GDRConnector gdrConnector = new GDRConnector();
        System.out.println(gdrConnector.connectionToDB());
    }

}
