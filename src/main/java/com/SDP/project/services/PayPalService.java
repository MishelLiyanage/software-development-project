//package com.SDP.project.services;
//
//import com.SDP.project.config.PaypalConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Base64;
//import java.util.Map;
//
//@Service
//public class PayPalService {
//
//    @Autowired
//    private PaypalConfig config;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    public String getAccessToken() {
//        String credentials = config.getClientId() + ":" + config.getClientSecret();
//        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.setBasicAuth(config.getClientId(), config.getClientSecret());
//
//        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
//        body.add("grant_type", "client_credentials");
//
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
//
//        ResponseEntity<Map> response = restTemplate.postForEntity(
//                config.getBaseUrl() + "/v1/oauth2/token", request, Map.class);
//
//        return response.getBody().get("access_token").toString();
//    }
//}
//
