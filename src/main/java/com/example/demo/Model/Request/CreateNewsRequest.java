package com.example.demo.Model.Request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateNewsRequest {
    private String News;
    private String NewsSubject;
    private String NewsSource;
}
