package com.controller;

import com.bean.CollinePosition;
import com.parsar.JsonToMap;
import com.services.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ParserController {

    Logger log = LoggerFactory.getLogger(ParserController.class);

    private final List<CollinePosition> collinePositionList = new ArrayList<>();
    private final Parser parser = new Parser();

    @PostMapping(path = "/colline/add/json")
    public ResponseEntity<String> addColline(@RequestBody String json){

        parser.parseJsonToMap(json);

        return ResponseEntity.status(200).body("Added successfully");

    }

    @GetMapping(path = "/colline/list/json")
    public String parseJson(){

        log.info("/colline/add services invoked");


        String jsonResponse = parser.parseToJson();

        return jsonResponse;
    }

    @PostMapping(path="/colline/add")
    public ResponseEntity<String> addColline(@RequestBody CollinePosition collinePosition){
        collinePositionList.add(collinePosition);
        return ResponseEntity.status(200).body("Successfully persisted...");
    }

    @GetMapping(path = "/colline/list")
    public String parseBeanToJson(){

        log.info("/colline/add services invoked");


        String jsonResponse = parser.parseToJson(collinePositionList);

        return jsonResponse;
    }

    @GetMapping(path = "colline/contract")
    public CollinePosition contract(){
        return new CollinePosition();
    }

}
