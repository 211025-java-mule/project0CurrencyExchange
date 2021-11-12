import org.springframework.stereotype.Component;

@Component
public interface CurrencyRepository {
    void create(Currency output);

}
