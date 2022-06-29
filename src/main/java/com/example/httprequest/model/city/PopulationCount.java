package com.example.httprequest.model.city;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
public class PopulationCount {

    @JsonProperty("year")
    private String year;
    @JsonProperty("value")
    private String value;
    @JsonProperty("sex")
    private String sex;
    @JsonProperty("reliabilty")
    private String reliabilty;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
