package se.jensen.yuki.webshop_admin.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class JsonFileWriter {
    private final ObjectMapper mapper = new ObjectMapper();

    public <T> void writeJsonFile(Path filePath, List<T> object) {
        try {
            mapper.writeValue(filePath.toFile(), object);
        } catch (IOException e) {
            throw new RuntimeException("File path is not valid");
        }
    }

    public <T> void writeJsonFile(Path filePath, List<T> object, Class<T> classType) {
        try {
            mapper.writerFor(mapper.getTypeFactory().constructCollectionType(List.class, classType)).writeValue(filePath.toFile(), object);
            //mapper.writeValue(filePath.toFile(), object);
        } catch (IOException e) {
            throw new RuntimeException("File path is not valid");
        }
    }
}
