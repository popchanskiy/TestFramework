package example.com.utills;

import com.github.curiousoddman.rgxgen.RgxGen;
import example.com.annotations.GenerateRule;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Random;

import static org.apache.commons.lang3.RandomUtils.nextInt;

public class DataGenerator {
    public static <T> T generate(Class<T> clazz) {
        try {
            T object = createObject(clazz);

            fillFields(object);

            return object;

        } catch (Exception e) {
            throw new RuntimeException("Cannot generate object for class: " + clazz.getName(), e);
        }
    }

    private static <T> T createObject(Class<T> clazz) throws Exception {
        Constructor<T> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);

        return constructor.newInstance();
    }

    private static void fillFields(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            GenerateRule annotation = field.getAnnotation(GenerateRule.class);

            if (annotation == null) {
                continue;
            }

            if (!field.getType().equals(String.class)) {
                throw new IllegalArgumentException(
                        "@GenerateRule can be used only with String fields: " + field.getName()
                );
            }

            String regex = annotation.value();
            String generatedValue = RgxGen.parse(regex).generate();

            field.setAccessible(true);
            field.set(object, generatedValue);
        }
    }
}
