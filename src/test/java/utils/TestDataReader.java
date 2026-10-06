package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class TestDataReader {

    public static <T> T readTestData(String filePath, Class<T> clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(filePath), clazz);
        } catch (IOException e) {
            throw new RuntimeException("Test verisi okunamadı: " + filePath, e);
        }
    }
}