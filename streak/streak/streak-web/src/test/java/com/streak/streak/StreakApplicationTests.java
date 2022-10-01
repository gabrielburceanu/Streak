package com.streak.streak;

import com.streak.streak.application.port.in.DistributedMapService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.time.Duration;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class StreakApplicationTests {

	@Autowired
	DistributedMapService distributedMapService;

	@Test
	void contextLoads() {

		Flux.range(0,100)
				.delayElements(Duration.ofMillis(1000))
				.map(d -> d * 2);

		Integer first = Flux.fromArray(new Integer[]{1, 2, 3}).blockFirst();
		System.out.println(first);

		distributedMapService.put("firstTestKey", "result");
		assertEquals("MapService not working", distributedMapService.get("firstTestKey"), "result");
	}

}
