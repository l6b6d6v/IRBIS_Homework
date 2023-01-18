package com.example.demo.Model.Response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NewsSubjectResponse {
    private String NewsSubject;
}
