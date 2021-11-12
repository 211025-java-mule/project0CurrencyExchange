import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CurrencyService {

    private ObjectMapper mapper;
    private URL url;
    private final static Logger log = LoggerFactory.getLogger(ApplicationContext.class.getName());

    public CurrencyService(ObjectMapper mapper, String url) {
        this.mapper = mapper;
        this.url = getUrl(url);
    }

    public Currency getCurrency() {
        HttpURLConnection httpURLConnection = getHttpURLConnection(url);
        InputStream response = getInputStream(httpURLConnection);
        String body = getBody(response);
        return getOutput(mapper, body);
    }


    private static URL getUrl(String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            log.error("404 not found");
        }
        return url;
    }

    private static HttpURLConnection getHttpURLConnection(URL url) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            log.error("400 Error connecting");
        }
        return connection;
    }

    private static InputStream getInputStream(HttpURLConnection connection) {
        InputStream response = null;
        try {
            response = connection.getInputStream();
        } catch (IOException e) {
            log.error("Could not open response");
        }
        return response;
    }

    private static String getBody(InputStream response) {
        String body = null;
        try {
            body = new String(response.readAllBytes());
        } catch (IOException e) {
            log.error("Could not read response");
        }
        return body;
    }

    private static Currency getOutput(ObjectMapper mapper, String body) {
        Currency output = new Currency();
        try {
            output = mapper.readValue(body, Currency.class);
        } catch (JsonProcessingException e) {
            log.error("Could not read response");
        }
        return output;
    }


}
