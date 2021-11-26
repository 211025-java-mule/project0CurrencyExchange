import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CurrencyFileRepository {

    private final static Logger log = LoggerFactory.getLogger(ApplicationContext.class.getName());
    private ObjectMapper objectMapper;

    public CurrencyFileRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private static String getSerializedString(ObjectMapper mapper, Currency output) {
        String serializedString= null;
        try {
            serializedString = mapper.writeValueAsString(output);
        } catch (JsonProcessingException e) {
            log.error("Could not serialize");
        }
        return serializedString;
    }
    public void writeToFile(Currency output) {
        File outputFile = new File("output.txt");
        try(FileWriter outpuFileWriter = new FileWriter(outputFile, true);	) {
            outpuFileWriter.write(getSerializedString(objectMapper, output) + "\n");
        } catch (IOException e) {
            log.error("Output file error");
        }
    }
}
