package cn.teamstack.utils;

public class StringUtil {

    public static boolean isEmpty(String value) {
        if (value == null || "".equals(value)) {
            return true;
        }
        return false;
    }

    /**
     * 将字符串第一个字符后用* 代替
     *
     * @param name
     * @return
     */
    public static String formatName(String name) {
        if (name == null) {
            return name;
        }
        String resName = name.substring(0, 1);
        for (int i = 1; i < name.length(); i++) {
            resName += "*";
        }
        return resName;

    }

    /**
     * 格式化手机 隐藏中间4位
     *
     * @param phoneNum
     * @return
     */
    public static String formatPhone(String phoneNum) {
        if (phoneNum == null || phoneNum.length() != 11) {
            return phoneNum;
        }
        phoneNum = phoneNum.substring(0, 3) + "****" + phoneNum.substring(7, 11);
        return phoneNum;
    }

    /**
     * 取前后4位 中间隐藏
     *
     * @param cardNumber
     * @return
     */
    public static String formatCard(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 8) {
            return cardNumber;
        }
        int length = cardNumber.length();
        String cardNumberStart = cardNumber.substring(0, 4);
        String cardNumberEnd = cardNumber.substring(cardNumber.length() - 4, cardNumber.length());
        StringBuffer sb = new StringBuffer();
        sb.append(cardNumberStart);
        for (int i = 0; i < length - 8; i++) {
            sb.append("*");
        }
        sb.append(cardNumberEnd);
        return sb.toString();
    }

}
