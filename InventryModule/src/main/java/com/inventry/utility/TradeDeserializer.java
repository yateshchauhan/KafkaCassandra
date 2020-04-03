package com.inventry.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventry.service.Trade;
import org.apache.kafka.common.serialization.Deserializer;
import java.util.Map;

public class TradeDeserializer implements Deserializer<Trade> {

    @Override
    public Trade deserialize(String s, byte[] bytes) {

        ObjectMapper mapper = new ObjectMapper();
        Trade trade = null;

        try {
            trade = mapper.readValue(bytes, Trade.class);
            return trade;
        }catch (Exception e){
            System.out.println("Failed while deserializing,"+e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }
}
