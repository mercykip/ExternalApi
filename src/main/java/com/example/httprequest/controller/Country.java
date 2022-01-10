package com.example.httprequest.controller;

import com.example.httprequest.util.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/country")
public class Country {

    @GetMapping
    public ResponseEntity<?> getCountry() {
        try {

            HttpRequest request = HttpRequest
                    .get("https://countriesnow.space/api/v0.1/countries")
                    .connectTimeout(120000);
            String res = request.body();
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}