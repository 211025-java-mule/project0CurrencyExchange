import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.assertEquals;

public class CurrencyDbTest {

    static Connection connection;

    @Before
    public void setUpConnection() {

        try {
            connection = DriverManager
                    .getConnection("jdbc:h2:mem:test;INIT=runscript from 'classpath:schema.sql'", "sa", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void checkConnection() throws SQLException {
        Assert.assertNotEquals(connection, null);
    }
    @Test
    public void testNumberOfColumnsInRate() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from rate;");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        assertEquals(4, resultSetMetaData.getColumnCount());
    }
    @Test
    public void testNumberOfColumnsInCurrency() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from currency;");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        assertEquals(5, resultSetMetaData.getColumnCount());
    }
    @Test
    public void testIfCurrencyIsUSD() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("insert into currency(base) values ('USD')");
        ResultSet resultSet = statement.executeQuery("select * from currency where base = 'USD'");
        while (resultSet.next()) {
            assertEquals("USD", resultSet.getString("base"));
        }
    }
}
