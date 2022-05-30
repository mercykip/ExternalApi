package com.example.httprequest.controller;

import com.example.httprequest.model.AirlineModel;
import com.example.httprequest.util.HttpRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping(value = "/country")
public class Country {
    @Autowired
    RestTemplate restTemplate;

    private static final Logger logger = LogManager.getLogger(Country.class);

    @GetMapping
    public ResponseEntity<?> getCountry() {
        try {
            HttpRequest request = HttpRequest
                    .get("https://api.instantwebtools.net/v1/airlines")
                    .connectTimeout(120000);
            String res = request.body();
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/restCountry")
    public ResponseEntity<?> getCountrys() {
        try {
            String uri="https://api.instantwebtools.net/v1/airlines";
            String result = restTemplate.getForObject(uri, String.class);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Post data to an external url
    @PostMapping("/airline_rest")
    public ResponseEntity<?> createAirlineRest(@RequestBody AirlineModel body) {
        try {

            String uri="https://api.instantwebtools.net/v1/airlines";

            ResponseEntity<String> result = restTemplate.postForEntity(uri, body, String.class);

            return new ResponseEntity<>( result.getStatusCodeValue() == 200 ? "Airline created successfully" : "Airline Not created successfully", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/airline_http")
    public ResponseEntity<?> createAirlineHttp(@RequestBody AirlineModel body) {
        try {
            String uri="https://api.instantwebtools.net/v1/airlines";
            logger.info("REQUEST BODY :"+ConvertToJson.setJsonString(body));

                 HttpRequest results =   HttpRequest.post(uri)
                         .header("Content-Type", "application/json")
                         .send(ConvertToJson.setJsonString(body)) ;

                 int status= results.code();
                 logger.info("STATUS CODE :"+status);

            return new ResponseEntity<>( status == 200 ? "Airline created successfully" : "Airline Not created successfully",status == 200 ? HttpStatus.OK: HttpStatus.BAD_REQUEST);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
