package com.befiring.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.text.SimpleDateFormat;

public class JsonUtil {
    public static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        //设置将对象转换成JSON字符串时候:包含的属性不能为空或"";
        //Include.Include.ALWAYS 默认
        //Include.NON_DEFAULT 属性为默认值不序列化
        //Include.NON_EMPTY 属性为 空（""）  或者为 NULL 都不序列化
        //Include.NON_NULL 属性为NULL 不序列化
//        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
        //设置将MAP转换为JSON时候只转换值不等于NULL的
//        mapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);
        mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        //设置有属性不能映射成PO时不报错
//        mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }
}
