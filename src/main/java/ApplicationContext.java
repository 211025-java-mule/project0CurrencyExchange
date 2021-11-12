import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class ApplicationContext {

    private final static Logger log = LoggerFactory.getLogger(ApplicationContext.class.getName());

    public CurrencyPostgresRepository getCurrencyPostgresRepository() {
        return currencyPostgresRepository;
    }

    private final CurrencyPostgresRepository currencyPostgresRepository;
    private ObjectMapper mapper;

    public Properties getProperties() {
        return properties;
    }

    private Properties properties;
    private CurrencyService currencyService;

    public CurrencyFileRepository getCurrencyFileRepository() {
        return currencyFileRepository;
    }

    private CurrencyFileRepository currencyFileRepository;


    public ObjectMapper getMapper() {
        return mapper;
    }

    public CurrencyService getCurrencyService() {
        return currencyService;
    }


    public ApplicationContext(String[] args) throws SQLException {
        this.properties = new Properties();
        this.mapper = new ObjectMapper();
        argsParser(args);
        this.currencyService = new CurrencyService(mapper,properties.getProperty("url"));
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "password");
        this.currencyPostgresRepository = new CurrencyPostgresRepository(connection);
        this.currencyFileRepository = new CurrencyFileRepository(mapper);



    }

    private void argsParser(String[] args) {
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-d":
                        properties.setProperty("saveToDB", "false");
                        break;
                    case "-t":
                        properties.setProperty("saveToTxt", "false");
                        break;
                    case "--url":
                        i++;
                        properties.setProperty("url", args[i]);
                        break;
                    default:
                        log.trace("Unknown argument");
                        break;
                }
            }
        } else {
            try {
                properties.load(Currency.class.getClassLoader().getResourceAsStream("application.properties"));

            } catch (IOException e) {
                System.err.println("Configuration file not found");
            } catch (NullPointerException e){
                log.error("File not found");
            }
        }
    }
}
