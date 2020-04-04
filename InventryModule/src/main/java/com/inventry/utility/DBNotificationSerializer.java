package com.inventry.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventry.bean.DbNotification;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class DBNotificationSerializer implements Serializer<DbNotification> {

    @Override
    public byte[] serialize(String s, DbNotification notification) {

        byte[] serialize = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            serialize = objectMapper.writeValueAsBytes(notification);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return serialize;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }
}
