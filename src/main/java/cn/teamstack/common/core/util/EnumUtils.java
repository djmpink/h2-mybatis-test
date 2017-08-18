package cn.teamstack.common.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public final class EnumUtils {
    private static final Logger LOG = LoggerFactory.getLogger(EnumUtils.class);

    private EnumUtils() {
    }

    public static <T extends CustomEnum> T realVal(Object value, Class<T> clazz) {
        for (T enumVal : clazz.getEnumConstants()) {
            if (enumVal.realVal().equals(value)) {
                return enumVal;
            }
        }
        throw new IllegalArgumentException(value + " is not valid of enum " + clazz);
    }

    public static <T extends Enum<T>> void changeNameTo(T enumInstance, String value) {
        try {
            Field fieldName = enumInstance.getClass().getSuperclass().getDeclaredField("name");
            fieldName.setAccessible(true);
            fieldName.set(enumInstance, value);
            fieldName.setAccessible(false);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public interface CustomEnum<T> {
        T realVal();
    }
}
