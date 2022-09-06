package com.nbp.api.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ProfileServiceController {

    @Autowired
    RestTemplate restTemplate;

    @PostMapping(path = "/profile")
    public ResponseEntity<Object> createProfile(@RequestBody Object profile){
        setProxyProperties();
        return restTemplate.postForEntity("http://profile-service:8080/profile", profile, Object.class);
    }

    @GetMapping(path = "/profile/{username}")
    public ResponseEntity<Object> getProfile(@PathVariable String username){
        setProxyProperties();
        return restTemplate.getForEntity("http://profile-service:8080/profile/" + username, Object.class);
    }

    @GetMapping(path = "/users")
    public ResponseEntity<Object[]> getAllProfile(){
        setProxyProperties();
        return restTemplate.getForEntity("http://profile-service:8080/users", Object[].class);
    }

    @PatchMapping(path = "/profile")
    public ResponseEntity<Object> updateProfile(@RequestBody Object profile){
        setProxyProperties();
        return ResponseEntity.ok(restTemplate.patchForObject("http://profile-service:8080/profile", profile, Object.class));
    }

    @DeleteMapping(path = "/profile/{username}")
    public void deleteProfile(@PathVariable String username){
        setProxyProperties();
        restTemplate.delete("http://profile-service:8080/profile/" + username);
    }

    private void setProxyProperties() {
        System.setProperty("proxyHost", "profile-service");
        System.setProperty("proxyPort", "8080");
    }
}
