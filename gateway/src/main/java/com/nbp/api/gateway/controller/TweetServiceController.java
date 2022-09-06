package com.nbp.api.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class TweetServiceController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(path = "/tweet/username/{username}/createdAt/{createdAt}")
    ResponseEntity<Object> getTweet(@PathVariable String username, @PathVariable String createdAt) {
        setProxyProperties();
        return restTemplate.getForEntity("http://tweet-service:8080/username/" +
                username +
                "/" +
                "createdAt/" +
                createdAt, Object.class);
    }

    @PostMapping(path = "/tweet")
    public ResponseEntity<Object> createTweet(@RequestBody Object tweet) {
        setProxyProperties();
        return restTemplate.postForEntity("http://tweet-service:8080/tweet",tweet, Object.class);
    }

    @GetMapping(path = "/tweet/username/{username}")
    public ResponseEntity<Object[]> getTweets(@PathVariable String username){
        setProxyProperties();
        return restTemplate.getForEntity("http://tweet-service:8080/username/" + username, Object[].class);
    }



    @PatchMapping(path = "/tweet")
    public ResponseEntity<Object> updateTweets(@RequestBody Object updatedTweet){
        setProxyProperties();
        return ResponseEntity.ok(restTemplate.patchForObject("http://tweet-service:8080/tweet", updatedTweet, Object.class));
    }

    @DeleteMapping(path = "/tweet/username/{username}/createdAt/{createdAt}/retweetedFrom/{retweetedFrom}")
    public void deleteTweet(@PathVariable String username, @PathVariable String createdAt, @PathVariable String retweetedFrom ){
        setProxyProperties();
        restTemplate.delete("http://tweet-service:8080/username/" +
                username +
                "/" +
                "createdAt/" +
                createdAt+
                "/retweetedFrom/"+
                retweetedFrom);
    }

    private void setProxyProperties() {
        System.setProperty("proxyHost", "tweet-service");
        System.setProperty("proxyPort", "8080");
    }

}
