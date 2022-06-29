package com.example.httprequest.service;

import com.example.httprequest.model.city.DataModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class CountryService {
    @Autowired
    WebClient webClient;

    private static final Logger logger = LogManager.getLogger(CountryService.class);

    public Flux<DataModel> getCities() {
        return webClient.get()
                .uri("https://countriesnow.space/api/v0.1/countries/population/cities")
                .retrieve()
                .bodyToFlux(DataModel.class)
                .doOnError(throwable -> logger.error("Failed for some reason", throwable));
    }
}
