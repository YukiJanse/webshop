package se.jensen.yuki.webshop_admin.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class JsonFileWriter {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> void writeJsonFile(Path filePath, List<T> object) {
        try {
//            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
//            System.out.println(json);
//            System.out.println(object);
            mapper.writeValue(filePath.toFile(), object);
        } catch (IOException e) {
            throw new RuntimeException("File path is not valid");
        }
    }

    public static <T> void writeJsonFile(Path filePath, List<T> object, Class<T> classType) {
        try {
//            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
//            System.out.println(json);
//            System.out.println(object);
            mapper.writerFor(mapper.getTypeFactory().constructCollectionType(List.class, classType)).writeValue(filePath.toFile(), object);
            //mapper.writeValue(filePath.toFile(), object);
        } catch (IOException e) {
            throw new RuntimeException("File path is not valid");
        }
    }
}
