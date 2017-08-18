package cn.teamstack.common.core.util;


import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * Created by zhouli on 2016/11/28.
 */
public class IdentityHelper {
    public static String createId() {
        return UUID.randomUUID().toString();
    }

    public static Boolean verifyId(String id) {
        if (StringUtils.isEmpty(id)) {
            return false;
        } else if (id.length() != 36) {
            return false;
        } else {
            return true;//并非唯一确认，还需其他验证
        }
    }

    public static String createToken() {
        return UUID.randomUUID().toString();
    }

    public static String get32UUID() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }
}
