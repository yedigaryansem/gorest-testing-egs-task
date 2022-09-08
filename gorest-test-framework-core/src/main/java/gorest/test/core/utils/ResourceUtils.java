package gorest.test.core.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import gorest.test.core.metadata.HttpResourcePath;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public interface ResourceUtils {
    static String extractPathSegment(Class<?> resourceType) {
        return resourceType.getAnnotation(HttpResourcePath.class).value();
    }

    static <T> Map<String, String> extractSearchParams(T searchObject) {
        Class<?> searchObjectClass = searchObject.getClass();
        Field[] searchFields = searchObjectClass.getDeclaredFields();
        Map<String, String> result = new HashMap<>(searchFields.length);
        for (Field searchField : searchFields) {
            JsonProperty searchParamName = searchField.getAnnotation(JsonProperty.class);
            if (searchParamName != null) {
                result.put(searchParamName.value(), extractValue(searchObject, searchField));
            }
        }
        return result;
    }

    private static <T> String extractValue(T object, Field field) {
        try {
            return field.get(object).toString();
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot access search field value.", e);
        }
    }
}
