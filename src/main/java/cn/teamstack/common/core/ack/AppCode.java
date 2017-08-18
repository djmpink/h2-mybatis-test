package cn.teamstack.common.core.ack;

/**
 * Created by zhouli on 2016/9/29.
 */

import com.google.common.collect.Lists;
import cn.teamstack.common.core.util.EnumUtils;
import cn.teamstack.common.core.util.KeyValPair;

import java.util.List;

public enum AppCode implements EnumUtils.CustomEnum<String> {

    MAPI_CODE("MAPI_CODE");


    private String value;

    AppCode(String value) {
        this.value = value;
    }

    @Override
    public String realVal() {
        return value;
    }

    public static List<KeyValPair> toPairList() {
        List<KeyValPair> serviceList = Lists.newArrayList();
        for (AppCode serviceCode : AppCode.values()) {
            serviceList.add(new KeyValPair(serviceCode.name(), serviceCode.realVal()));
        }
        return serviceList;
    }
}

