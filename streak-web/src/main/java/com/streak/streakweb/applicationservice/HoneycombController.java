package com.streak.streakweb.applicationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path =  "api/v1")
public class HoneycombController {

  @Autowired
  RestTemplate restTemplate;

  @GetMapping("/dummy")
  public DummyClass getDummyClass(@RequestParam(value = "index", defaultValue = "0") String index) {
    int i = Integer.parseInt(index);

    if (i > 100) {
      return new DummyClass("Last", "Dude" + index);
    }

    String streakClientTripleRequestUrl = System.getenv("STREAK_CLIENT_ENDPOINT") + "triple?index=" + (i*10);

    Integer tripleIndex = restTemplate.getForObject(streakClientTripleRequestUrl, Integer.class);

    return new DummyClass("John", "Rambo" + tripleIndex);
  }
}
