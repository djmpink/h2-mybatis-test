package cn.teamstack.utils;

import java.util.Base64;

/**
 * Created by zhouli on 2017/3/8.
 */
public class TicketUtil {
    public static String generateTicket(String subject, String email) {
        String strToEncode = subject + "_" + email + "_" + DateUtil.getNowTime();
        Base64.Encoder encoder = Base64.getUrlEncoder();
        return encoder.encodeToString(strToEncode.getBytes());
    }
}
