package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
	@GetMapping
	public String hello() {
		return "helloworld!!";
	}
	
	@PostMapping
	public String post(@RequestBody String username) {
		System.out.println("username: "+username);
		return "helloworld!! "+username;
	}
}
