package com.djdu.common.Tool;


import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * @ClassName JsonXMLUtils
 * @Description TODO json转object工具，转换成需要的对象
 * @Author DJDU
 * @Date 2019/2/12 17:41
 * @Version 1.0
 **/
public class JsonXMLUtils {
    public static String obj2json(Object obj) throws Exception {
        return JSON.toJSONString(obj);
    }

    public static <T> T json2obj(String jsonStr, Class<T> clazz) throws Exception {
        return JSON.parseObject(jsonStr, clazz);
    }

    public static <T> Map<String, Object> json2map(String jsonStr)     throws Exception {
        return JSON.parseObject(jsonStr, Map.class);
    }

    public static <T> T map2obj(Map<?, ?> map, Class<T> clazz) throws Exception {
        return JSON.parseObject(JSON.toJSONString(map), clazz);
    }
}
