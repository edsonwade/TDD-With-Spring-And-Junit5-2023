//package code.vanilson.marketplace.config;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//
//import java.sql.*;
//
//import static org.easymock.EasyMock.*;
//
//class GDRConnectorTest {
//    private Connection mockConnection;
//    private Statement mockStatement;
//    private ResultSet mockResultSet;
//
//    @Before
//    public void setUp() throws Exception {
//        // Create mock objects
//        mockConnection = createMock(Connection.class);
//        mockStatement = createMock(Statement.class);
//        mockResultSet = createMock(ResultSet.class);
//
//        // Set up expectations
//        expect(DriverManager.getConnection(anyString(), anyString(), anyString()))
//                .andReturn(mockConnection);
//        expect(mockConnection.createStatement()).andReturn(mockStatement);
//        expect(mockStatement.executeQuery(anyString())).andReturn(mockResultSet);
//
//        // Define the behavior of the result set
//        expect(mockResultSet.next()).andReturn(true).times(2);
//        expect(mockResultSet.getString("column1")).andReturn("Value 1");
//        expect(mockResultSet.getInt("column2")).andReturn(100);
//        expect(mockResultSet.next()).andReturn(false);
//
//        // Replay the mock objects
//        replay(mockConnection, mockStatement, mockResultSet);
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        // Verify and reset the mock objects
//        verify(mockConnection, mockStatement, mockResultSet);
//        reset(mockConnection, mockStatement, mockResultSet);
//    }
//
//    @Test
//    public void testOracleConnection() throws Exception {
//        // Create an instance of the OracleConnection class
//        OracleConnection oracleConnection = new OracleConnection(mockConnection);
//
//        // Call the executeQuery method
//        ResultSet result = oracleConnection.executeQuery();
//
//        // Verify the result
//        assertTrue(result.next());
//        assertEquals("Value 1", result.getString("column1"));
//        assertEquals(100, result.getInt("column2"));
//        assertFalse(result.next());
//    }
//
//    // Inner class representing OracleConnection
//    private static class OracleConnection {
//        private Connection connection;
//        private static final String JDBC_URL = "jdbc:mysql://mindswap-mysql:3306/mastering_db_2022";
//        private static final String USERNAME = "root";
//        private static final String PASSWORD = "mypass";
//
//        public OracleConnection(Connection connection) throws SQLException {
//            connection = DriverManager.getConnection(JDBC_URL,USERNAME,PASSWORD);
//            this.connection = connection;
//        }
//
//        public ResultSet executeQuery() throws Exception {
//            Statement statement = connection.createStatement();
//            return statement.executeQuery("SELECT * FROM your_table");
//        }
//    }
//}
