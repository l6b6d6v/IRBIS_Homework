package com.example.demo.Model.Request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NewsRequest {
    private String street;
    private String city;
    private String building;
}
