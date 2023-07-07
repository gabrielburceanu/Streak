package com.streak.streakweb.applicationservice;

import com.streak.streakweb.domain.CallsCounterService;
import com.streak.streakweb.domain.DistributedMapRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@PreAuthorize("permitAll()")
//@PreAuthorize("isAuthenticated()")
@Slf4j
@AllArgsConstructor
public class ReactiveController {

    private final CallsCounterService callsCounterService;
    private final DistributedMapRepository redissonService;

    @GetMapping(path = "/")
    public String index() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info("Info logs enabled");
        log.debug("Debug logs enabled");
        log.error("Error logs enabled");

        String methodMapping = new Object() {
        }.getClass().getEnclosingMethod().getAnnotation(GetMapping.class).path()[0];
        callsCounterService.add(methodMapping);
        return "Streak-web index page";
    }

    @PreAuthorize("permitAll()")
    @GetMapping(path = "/some-number")
    public Integer number() {

        String methodMapping = new Object() {
        }.getClass().getEnclosingMethod().getAnnotation(GetMapping.class).path()[0];
        callsCounterService.add(methodMapping);

        return 22;
    }

    @PreAuthorize("permitAll()")
    @GetMapping(path = "v1/push")
    public String redisson(@RequestParam String key, @RequestParam String value) {
        redissonService.put(key, value);

        return "Pushed";
    }

    @PreAuthorize("permitAll()")
    @GetMapping(path = "v1/get")
    public String getRedisson(@RequestParam String key) {
        return redissonService.get(key);
    }

    @PreAuthorize("permitAll()")
    @GetMapping(path = "/nonreactive")
    public Integer nonreactive() {
        GetMapping annotation = new Object() {
        }.getClass().getEnclosingMethod().getAnnotation(GetMapping.class);

        String methodMapping = new Object() {
        }.getClass().getEnclosingMethod().getAnnotation(GetMapping.class).path()[0];
        callsCounterService.add(methodMapping);

        return 7777;
    }

    @PreAuthorize("permitAll()")
    @GetMapping(path = "/custom_prometheus",
            produces = "text/plain")
    public String customPrometheus() {
        Map<String, Integer> calls = callsCounterService.getCalls();

        StringBuilder response = new StringBuilder();
        calls.forEach(
                (url, count) -> {
                    String prom_name = url.replace("/", "gabi_");
                    response.append("# HELP ").append(prom_name).append(" The peak live thread count since the Java virtual machine started or peak was reset\n");
                    response.append("# TYPE ").append(prom_name).append(" gauge\n");
                    response.append(prom_name).append(" ").append(count).append("\n");
                }
        );

        return response.toString();
    }

//    @PreAuthorize("hasAuthority('appr')")
//    @GetMapping(path = "/approle")
//    public String approle() {
//        return "HasAppRole!!!";
//    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/admin")
    public String adminReactive() {

        String methodMapping = new Object() {
        }.getClass().getEnclosingMethod().getAnnotation(GetMapping.class).path()[0];
        callsCounterService.add(methodMapping);
        return "Who is big admin ?";
    }
}