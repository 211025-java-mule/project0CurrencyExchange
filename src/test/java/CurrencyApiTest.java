import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyApiTest {

    static URL url;
    static HttpURLConnection connection;

    @Before
    public void setUpConnection() {
        try {
            url = new URL("http://api.exchangeratesapi.io/v1/latest?access_key=cd8c7a713e1dc36d481f2d94c0be8cb6");
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnectionToAPI() {
        Assert.assertNotEquals(null, connection);
    }

    @Test
    public void testResponseFromAPI() throws IOException {
        InputStream response = connection.getInputStream();
        String body = new String(response.readAllBytes());
        Assert.assertNotEquals(null, body);
    }

}
