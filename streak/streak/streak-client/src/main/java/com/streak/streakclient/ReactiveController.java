package com.streak.streakclient;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("permitAll()")
//@PreAuthorize("isAuthenticated()")
public class ReactiveController {

    RedissonService redissonService;

    public ReactiveController(RedissonService redissonService) {
        this.redissonService = redissonService;
    }

    @GetMapping(path = "/")
    public String index () {
        return "Streak-client index page";
    }

    @PreAuthorize("permitAll()")
    @GetMapping(path = "/number")
    public Integer number() {


        return 	22;
    }

    @PreAuthorize("permitAll()")
    @GetMapping(path = "/nonreactive")
    public Integer nonreactive() {
        return 7777;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/admin")
    public String adminReactive() {
        return "Who is big admin ?";
    }

    @PreAuthorize("permitAll()")
    @GetMapping(path = "/push")
    public String redisson(@RequestParam String key, @RequestParam String value) {
        redissonService.push(key, value);

        return "Pushed";
    }

    @PreAuthorize("permitAll()")
    @GetMapping(path = "/get")
    public String getRedisson(@RequestParam String key) {
        return redissonService.get(key);
    }
}
