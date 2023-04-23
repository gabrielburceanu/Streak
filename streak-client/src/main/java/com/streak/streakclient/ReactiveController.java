package com.streak.streakclient;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("permitAll()")
@AllArgsConstructor
//@PreAuthorize("isAuthenticated()")
public class ReactiveController {

    RedissonService redissonService;

    @GetMapping(path = "/")
    public String index () {
        return "Streak-client index page";
    }

    @PreAuthorize("permitAll()")
    @GetMapping(path = "/triple")
    public Integer triple(@RequestParam(value = "index", defaultValue = "0") String index) {
        return 3 * Integer.parseInt(index);
    }

    @PreAuthorize("permitAll()")
    @GetMapping(path = "/nonreactive")
    public Integer nonreactive() {
        return 7777;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/admin")
    public String admin() {
        return "Who is big admin ?";
    }

    @PreAuthorize("permitAll()")
    @GetMapping(path = "/push")
    public String redisson(@RequestParam String key, @RequestParam String value) {
        redissonService.put(key, value);

        return "Pushed";
    }

    @PreAuthorize("permitAll()")
    @GetMapping(path = "/get")
    public String getRedisson(@RequestParam String key) {
        return redissonService.get(key);
    }
}