package com.example.demo.Model.Request;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private String login;
    private String password;
}