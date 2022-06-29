package com.example.httprequest.model.city;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class DataModel {
    @JsonProperty("error")
    private Boolean error;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("data")
    private List<Datum> data;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
