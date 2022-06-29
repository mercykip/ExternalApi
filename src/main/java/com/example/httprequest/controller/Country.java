package com.example.httprequest.controller;

import com.example.httprequest.model.AirlineModel;
import com.example.httprequest.service.CountryService;
import com.example.httprequest.util.HttpRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping(value = "/country")
public class Country {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CountryService countryService;

    private static final Logger logger = LogManager.getLogger(Country.class);

    @GetMapping
    public ResponseEntity<?> getCountry() {
        try {
            HttpRequest request = HttpRequest
                    .get("https://countriesnow.space/api/v0.1/countries/population/cities")
                    .connectTimeout(12000);
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
            String uri="https://countriesnow.space/api/v0.1/countries/population/cities";
            String result = restTemplate.getForObject(uri, String.class);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/web_client_country")
    public Flux<?> getCountry1() {
        try {
            return countryService.getCities();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
//    @GetMapping("/web_client_country")
//    public ResponseEntity<?> getCountry1() {
//        try {
//            String uri="https://countriesnow.space/api/v0.1/countries/population/cities";
////            HttpClient httpClient = HttpClient.create()
////                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
////                    .responseTimeout(Duration.ofMillis(5000))
////                    .doOnConnected(conn ->
////                            conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
////                                    .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));
//
//            WebClient client = (WebClient) WebClient
//                    .get()
//                    .uri(uri)
//                            .retrieve().bodyToFlux(CityModel.class);
////            logger.info(client);
//
////            client.get().retrieve().toBodilessEntity().block();
//            return new ResponseEntity<>(client, HttpStatus.OK);
//        }catch (Exception e){
//            e.printStackTrace();
//            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


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
