package cn.teamstack.common.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeyValPair {
    public String key;

    public String name;

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.PrettyFormat);
    }

    public KeyValPair() {
    }

    public KeyValPair(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public static List<KeyValPair> toPairList(Map<String, String> kvMap) {
        if (CollectionUtils.isNullOrEmpty(kvMap)) {
            return Lists.newArrayList();
        }
        return kvMap.entrySet()
                .stream()
                .map(entry -> new KeyValPair(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public static LinkedHashMap<String, String> toMap(List<KeyValPair> pairList) {
        LinkedHashMap<String, String> kvMap = Maps.newLinkedHashMap();
        if (!CollectionUtils.isNullOrEmpty(pairList)) {
            for (KeyValPair pair : pairList) {
                kvMap.put(pair.key, pair.name);
            }
        }
        return kvMap;
    }
}
