package com.streak.streakclient;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.time.Duration;

@SpringBootTest
class StreakClientApplicationTests {

	@Test
	void contextLoads() {

		Flux.range(0,100)
				.delayElements(Duration.ofMillis(1000))
				.map(d -> d * 2);

		Integer first = Flux.fromArray(new Integer[]{1, 2, 3}).blockFirst();
		System.out.println(first);
	}

}
