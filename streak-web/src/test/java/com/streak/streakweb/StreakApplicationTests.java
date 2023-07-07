package com.streak.streakweb;

import com.streak.streakweb.domain.DistributedMapRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.time.Duration;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class StreakApplicationTests {

	@Autowired
	DistributedMapRepository distributedMapRepository;

	@Test
	void contextLoads() {

		Flux.range(0,100)
				.delayElements(Duration.ofMillis(1000))
				.map(d -> d * 2);

		Integer first = Flux.fromArray(new Integer[]{1, 2, 3}).blockFirst();
		System.out.println(first);

		distributedMapRepository.put("firstTestKey", "result");
		assertEquals("MapService not working", distributedMapRepository.get("firstTestKey"), "result");
	}

}
