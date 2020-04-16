package com.services;

import com.bean.CollinePosition;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {

    private List<Map<String,String>> dataList = new ArrayList<>();

    public void parseJsonToMap(String json){

        Map<String,String> dataMap = null;

        ObjectMapper mapper = new ObjectMapper();
        try {
            dataMap = mapper.readValue(json, Map.class);
            dataList.add(dataMap);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public String parseToJson(){

        System.out.println(dataList);

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(dataList);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return  null;
    }

    public String parseToJson(List<CollinePosition> list){

        List<Map<String,String>> dataList = rows(list);
        System.out.println(dataList);

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(dataList);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return  null;
    }

    public static void main(String[] args) {
        Parser parser = new Parser();
        CollinePosition collinePosition = new CollinePosition();
        collinePosition.setBook("VB10");
        collinePosition.setCptyCode("cpty1109");
        collinePosition.setCptyName("SecFinance");
        collinePosition.setMessageId("Msg11110");
        collinePosition.setSubSystemId("Colline");

        CollinePosition collinePosition1 = new CollinePosition();
        collinePosition1.setBook("VB101");
        collinePosition1.setCptyCode("cpty11091");
        collinePosition1.setCptyName("SecFinance");
        collinePosition1.setMessageId("Msg111101");
        collinePosition1.setSubSystemId("Colline");

        parser.parseToJson(new ArrayList<CollinePosition>(){
            {
                add(collinePosition);
                add(collinePosition1);

            }
        });
    }

    private List<Map<String,String>> rows(List<CollinePosition> list){

        List<Map<String,String>> rows = new ArrayList<>();

        for(CollinePosition collinePosition : list){
            Map<String,String> row = new HashMap<>();
            rows.add(row);
            Class positionClass = collinePosition.getClass();
            Field[] fields = positionClass.getDeclaredFields();

            for(Field field : fields){

                Object value = invokedMethod(field.getName(),collinePosition);
                row.put(field.getName(), (String)value);


            }



        }
    return rows;
    }

    private String invokedMethod(String fieldName, CollinePosition collinePosition){
        String value = null;
        switch (fieldName){
            case "messageId":{
                value = collinePosition.getMessageId();
                break;
            }case "book":{
                value = collinePosition.getBook();
                break;
            }case "subSystemId":{
                value = collinePosition.getSubSystemId();
                break;
            }case "cptyName":{
                value = collinePosition.getCptyName();
                break;
            }case "cptyCode":{
                value = collinePosition.getCptyCode();
                break;
            }
        }
        return value;
    }

    private String getMethodName(Field field){
        String name = field.getName();
        StringBuffer newName = new StringBuffer();

        if(StringUtils.isNotBlank(name)){
            char firstChar = name.charAt(0);
            newName = newName.append("get").append(Character.toUpperCase(firstChar)).append(name.substring(1));
            System.out.println("fieldName : "+name+", newName : "+newName.toString());
            return newName.toString();
        }
        return null;
    }

    public Object invokedMethod(Class collinePositionRef , String methodName){

        // load the compiled class
        //URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { parentDirectory.toURI().toURL() });
        //Class<?> helloClass = classLoader.loadClass(classname);
        Object value = null;
        try {
            // call a method on the loaded class
            Method helloMethod = collinePositionRef.getDeclaredMethod(methodName);
            value = helloMethod.invoke(collinePositionRef.newInstance());
        }catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        }
        return  value;
    }
}
