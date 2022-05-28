package com.streak.streak;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Map;

@RestController
@PreAuthorize("permitAll()")
//@PreAuthorize("isAuthenticated()")
public class ReactiveController {

    CallsCounterService callsCounterService;

    public ReactiveController(CallsCounterService callsCounterService) {
        this.callsCounterService = callsCounterService;
    }

    @GetMapping(path = "/")
    public String index () {
        String methodMapping = new Object(){}.getClass().getEnclosingMethod().getAnnotation(GetMapping.class).path()[0];
        callsCounterService.add(methodMapping);
        return "index page";
    }

    @PreAuthorize("permitAll()")
    @GetMapping(path = "/number")
    public Integer number() {

        String methodMapping = new Object(){}.getClass().getEnclosingMethod().getAnnotation(GetMapping.class).path()[0];
        callsCounterService.add(methodMapping);

        return 	22;
    }

    @PreAuthorize("permitAll()")
    @GetMapping(path = "/nonreactive")
    public Integer nonreactive() {
        GetMapping annotation = new Object() {
        }.getClass().getEnclosingMethod().getAnnotation(GetMapping.class);

        String methodMapping = new Object(){}.getClass().getEnclosingMethod().getAnnotation(GetMapping.class).path()[0];
        callsCounterService.add(methodMapping);

        return 7777;
    }

    @PreAuthorize("permitAll()")
    @GetMapping(path = "/custom_prometheus",
            produces="text/plain")
    public String customPrometheus() {
        Map<String, Integer> calls = callsCounterService.getCalls();

        StringBuilder response = new StringBuilder();
        calls.forEach(
                (url, count) -> {
                    String prom_name = url.replace("/","gabi_");
                    response.append("# HELP " + prom_name + " The peak live thread count since the Java virtual machine started or peak was reset\n");
                    response.append("# TYPE " + prom_name + " gauge\n");
                    response.append(prom_name + " " + count + "\n");
                }
        );

        return response.toString();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/admin")
    public String adminReactive() {

        String methodMapping = new Object(){}.getClass().getEnclosingMethod().getAnnotation(GetMapping.class).path()[0];
        callsCounterService.add(methodMapping);
        return "Who is big admin ?";
    }
}
