package cn.teamstack.common.core.exception;

import cn.teamstack.common.core.ack.ACK;
import cn.teamstack.common.core.ack.AppCode;
import cn.teamstack.common.core.util.McodeHelper;

public class AppException extends RuntimeException {

    public final String code;
    public final String msg;

    public AppException(String code) {
        this.code = code;
        this.msg = "exception:" + code;
    }

    public AppException(AppCode appCode, ACK ack) {
        this.code = McodeHelper.mCode(appCode, ack.realVal());
        this.msg = ack.msg;
    }
}
