package com.nbp.api.gateway.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
public class TimelineServiceController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/timeline/{username}")
    public ResponseEntity<Object> getTimeline(@PathVariable String username){
        setProxyProperties();
        return restTemplate.getForEntity("http://timeline-service:8080/timeline/" + username, Object.class);
    }

    @GetMapping("/userline/{username}")
    public ResponseEntity<Object> getUserline(@PathVariable String username){
        setProxyProperties();
        return restTemplate.getForEntity("http://timeline-service:8080/userline/" + username, Object.class);
    }

    @GetMapping(path = "/tweets/{username}")
    public ResponseEntity<Object> getAllTweets(@PathVariable String username){
        setProxyProperties();
        return restTemplate.getForEntity("http://timeline-service:8080/tweets/" + username, Object.class);
    }

    @PostMapping("/{username}/follow/{follows}")
    public void followUser(@PathVariable String username, @PathVariable String follows){
        setProxyProperties();
        restTemplate.postForEntity("http://timeline-service:8080/" +
                username +
                "/follow/" +
                follows, null, Void.class);
    }

    @PostMapping("/{username}/unfollow/{unfollows}")
    public void unFollowUser(@PathVariable String username, @PathVariable String unfollows){
        setProxyProperties();
        restTemplate.postForEntity("http://timeline-service:8080/" +
                username +
                "/unfollow/" +
                unfollows, null, Void.class);
    }

    @GetMapping("/{username}/followers")
    public ResponseEntity<Object[]> getUserFollowers(@PathVariable String username){
        setProxyProperties();
        return restTemplate.getForEntity("http://timeline-service:8080/" + username + "/followers", Object[].class);
    }

    @GetMapping("/{username}/following")
    public ResponseEntity<Object[]> getUserFollowing(@PathVariable String username){
        setProxyProperties();
        return restTemplate.getForEntity("http://timeline-service:8080/" + username + "/following", Object[].class);
    }

    @GetMapping("/{username}/follows/{follows}")
    public ResponseEntity<Object> follows(@PathVariable String username, @PathVariable String follows){
        setProxyProperties();
        return restTemplate.getForEntity("http://timeline-service:8080/" +
                username +
                "/follows/" +
                follows, Object.class);
    }

    @PostMapping(path = "/like")
    public void likeTweet(@RequestBody Object tweetLikedDto) {
        setProxyProperties();
        restTemplate.postForEntity("http://timeline-service:8080/like",tweetLikedDto, Object.class);
    }

    @PostMapping(path = "/unlike")
    public void unlikeTweet(@RequestBody Object tweetUnlikedDto) {
        setProxyProperties();
        restTemplate.postForEntity("http://timeline-service:8080/unlike",tweetUnlikedDto, Object.class);
    }

    @PostMapping("/retweet/createdAt/{createdAt}/username/{username}/retweetedFrom/{retweetedFrom}/retweetCreatedAt/{retweetCreatedAt}")
    public void retweet(@PathVariable String username, @PathVariable String createdAt, @PathVariable String retweetedFrom, @PathVariable String retweetCreatedAt){
        setProxyProperties();
        restTemplate.postForEntity("http://timeline-service:8080/retweet/" +
                "createdAt/" +
                createdAt +
                "/username/" +
                username +
                "/retweetedFrom/" +
                retweetedFrom +
                "/retweetCreatedAt/" +
                retweetCreatedAt
                ,null, Object.class);
    }

    @PostMapping("/retweet/deleted/createdAt/{createdAt}/username/{username}/retweetedFrom/{retweetedFrom}")
    public void retweetDeleted(@PathVariable String username, @PathVariable String createdAt, @PathVariable String retweetedFrom){
        setProxyProperties();
        restTemplate.postForEntity("http://timeline-service:8080/retweet/deleted/" +
                "createdAt/" +
                createdAt +
                "/username/" +
                username +
                "/retweetedFrom/" +
                retweetedFrom,null, Object.class);
    }

    @PostMapping("/liked/createdAt/{createdAt}/username/{username}/originalOwnerUsername/{originalOwnerUsername}")
    public ResponseEntity<Object> liked(@PathVariable String username, @PathVariable String createdAt, @PathVariable String originalOwnerUsername){
        setProxyProperties();
        return restTemplate.postForEntity("http://timeline-service:8080/liked/" +
                "createdAt/" +
                createdAt +
                "/username/" +
                username +
                "/originalOwnerUsername/" +
                originalOwnerUsername,null, Object.class);
    }

    @PostMapping("/retweeted/createdAt/{createdAt}/username/{username}/originalOwnerUsername/{originalOwnerUsername}")
    public ResponseEntity<Object> retweeted(@PathVariable String username, @PathVariable String createdAt, @PathVariable String originalOwnerUsername){
        setProxyProperties();
        return restTemplate.postForEntity("http://timeline-service:8080/retweeted/" +
                "createdAt/" +
                createdAt +
                "/username/" +
                username +
                "/originalOwnerUsername/" +
                originalOwnerUsername,null, Object.class);
    }

    private void setProxyProperties() {
        System.setProperty("proxyHost", "timeline-service");
        System.setProperty("proxyPort", "8080");
    }
}
