import java.sql.SQLException;


public class ExchangeRate{
    public static void main(String[] args) throws SQLException {
        ApplicationContext applicationContext = new ApplicationContext(args);
        Currency currency = applicationContext.getCurrencyService().getCurrency();
        if(applicationContext.getProperties().getProperty("saveToTxt").equals("true")){
            CurrencyFileRepository currencyFileRepository = applicationContext.getCurrencyFileRepository();
            currencyFileRepository.writeToFile(currency);
        }
        if(applicationContext.getProperties().getProperty("saveToDB").equals("true")){
            applicationContext.getCurrencyPostgresRepository().create(currency);
        }



    }

}
