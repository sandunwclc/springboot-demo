package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootgithubdemoApplicationTests {

	@Test
	void contextLoads() {
		String expected="1";
		String actual="1";
		assertEquals(actual, expected);
	}

}
