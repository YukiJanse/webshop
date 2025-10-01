package se.jensen.yuki.webshop_admin.util;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonFileReader {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> List<T> readJson(Path filePath, Class<T> tClass) {
        List<T> returnList = new ArrayList<>();
        try {
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, tClass);
            returnList = mapper.readValue(filePath.toFile(), listType);
        } catch (DatabindException e) {
            throw new RuntimeException("JSON format is not valid.");
        } catch (IOException e) {
            throw new RuntimeException("Failed reading file.");
        }
        return returnList;
    }
}
