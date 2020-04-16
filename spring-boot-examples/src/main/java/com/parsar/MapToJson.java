package com.parsar;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class MapToJson {

    public static String parse(Map<String,String> map){

        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {

            // convert map to JSON string
            json = mapper.writeValueAsString(map);

            System.out.println(json);   // compact-print

            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);

            System.out.println(json);   // pretty-print

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
