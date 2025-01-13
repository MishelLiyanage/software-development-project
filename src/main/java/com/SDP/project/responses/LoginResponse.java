package com.SDP.project.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private long expiresIn;

    @Override
    public String toString() {
        return "LoginResponse{" +
                "token='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}