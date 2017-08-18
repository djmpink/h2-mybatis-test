package cn.teamstack.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by zhouli on 2017/1/9.
 */
public class MathUtils {
    private static final int DEFAULT_SCALE = 5;

    private MathUtils() {
    }

    public static String shuffleNum(int length) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(new Random().nextInt(10));
        }
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        list.forEach(sb::append);
        return sb.toString();
    }

    public static BigDecimal nvl(BigDecimal num) {
        return num == null ? BigDecimal.ZERO : num;
    }

    public static Long nvl(Long num) {
        return num == null ? 0L : num;
    }

    public static Integer nvl(Integer num) {
        return num == null ? 0 : num;
    }

    public static Boolean nvl(Boolean bool) {
        return null == bool ? Boolean.FALSE : bool;
    }

    public static BigDecimal limitScale(BigDecimal decimal) {
        return limitScale(decimal, DEFAULT_SCALE);
    }

    public static BigDecimal limitScale(BigDecimal decimal, int scale) {
        decimal = nvl(decimal);
        return decimal.scale() < scale ? decimal : decimal.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    public static Integer ceil(Long total, Integer size) {
        if (nvl(total) <= 0 || nvl(size) <= 0) {
            return 0;
        }
        return (int) (Math.ceil(total.doubleValue() / size.doubleValue()));
    }
}
