package com.streak.streak;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@PreAuthorize("permitAll()")
//@PreAuthorize("isAuthenticated()")
public class ReactiveController {

    @GetMapping(path = "/")
    public String index () {
        return "index page";
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
}
