package com.streak.streakclient.applicationservice;

import com.streak.streakclient.infra.redisson.RedissonRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path =  "/api/v1")
@AllArgsConstructor
public class ReactiveController {

    RedissonRepository redissonRepository;

    @GetMapping(path = "")
    public String index () {
        return "Streak-client index page";
    }

    @GetMapping(path = "/triple")
    public Integer triple(@RequestParam(value = "index", defaultValue = "0") String index) {
        return 3 * Integer.parseInt(index);
    }

    @GetMapping(path = "/nonreactive")
    public Integer nonreactive() {
        return 7777;
    }

    @GetMapping(path = "/admin")
    public String admin() {
        return "Who is big admin ?";
    }

    @GetMapping(path = "/push")
    public String redisson(@RequestParam String key, @RequestParam String value) {
        redissonRepository.put(key, value);

        return "Pushed";
    }

    @GetMapping(path = "/get")
    public String getRedisson(@RequestParam String key) {
        return redissonRepository.get(key);
    }
}
