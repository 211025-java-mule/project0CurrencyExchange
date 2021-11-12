import java.sql.Timestamp;
import java.util.Map;

public class Currency {
    public boolean success;
    public Timestamp timestamp;
    public String base;
    public String date;
    public Map<String, Double> rates;

    public boolean isSuccess() {
        return success;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getBase() {
        return base;
    }

    public String getDate() {
        return date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    @Override
    public String toString() {
        return "Root{" +
                "success=" + success +
                ", timestamp=" + timestamp +
                ", base='" + base + '\'' +
                ", date='" + date + '\'' +
                ", rates=" + rates.get("ALL") +
                '}';
    }
}
