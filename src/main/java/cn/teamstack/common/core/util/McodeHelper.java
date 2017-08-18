package cn.teamstack.common.core.util;


import org.apache.commons.lang3.StringUtils;

/**
 * Created by zhouli on 2016/11/28.
 */
public class McodeHelper {
    public McodeHelper() {
    }

    public static String mCode(int mc) {
        return "m" + leftPad7(mc);
    }

    public static String mCode(Enum prefix, int code) {
        return null == prefix ? mCode(code) : prefix.name() + "_" + leftPad7(code);
    }

    private static String leftPad7(int mc) {
        return StringUtils.leftPad(mc + "", 7, "0");
    }
}
