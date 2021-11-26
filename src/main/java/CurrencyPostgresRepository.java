import org.springframework.scheduling.annotation.Scheduled;

import java.sql.*;
import java.util.Map;

public class CurrencyPostgresRepository implements CurrencyRepository {

    private Connection connection;

    public CurrencyPostgresRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    @Scheduled(fixedDelay = 18000)
    public void create(Currency currency) {
        try {

            PreparedStatement ps = connection.prepareStatement(
                    "insert into currencyyy(success, timestamp, base, date)" +
                            " values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psRate = connection.prepareStatement(
                    "insert into ratee(name, value, currency)" +
                            " values (?, ?, ?);"
            );
            ps.setBoolean(1, currency.isSuccess());
            ps.setTimestamp(2, currency.getTimestamp());
            ps.setString(3, currency.getBase());
            ps.setString(4, currency.getDate());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            long i = generatedKeys.next() ? generatedKeys.getLong(1) : 0;
            for (Map.Entry<String, Double> entry : currency.getRates().entrySet()) {
                psRate.setString(1, entry.getKey());
                psRate.setDouble(2, entry.getValue());
                psRate.setLong(3, i);
                psRate.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
