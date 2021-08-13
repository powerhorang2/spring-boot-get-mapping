package com.example.hello.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hello.dto.UserRequest;

@RestController
@RequestMapping("/api/get")
public class GetApiController {
	
	@GetMapping(path = "/hello")
	public String getHello() {
		return "get Hello";
	}
	
	@RequestMapping(path = "/hi", method = RequestMethod.GET) // default: get, post, put, delete 다 적용됨
	public String hi() {
		return "hi";
	}
	
	// http://localhost:9090/api/get/path-variable/{name}
	// Path Variable 으로 받는 방식
	@GetMapping("/path-variable/{name}")
	public String pathVariable(@PathVariable(name = "name") String pathName) {
		
		System.out.println("PathVariable : " + pathName);
		
		return pathName;
	}
	
	// http://localhost:9090/api/get/query-param?user={user}&email={email@example.com}&age={age}
	// Query Param Map으로 받는 방식
	@GetMapping(path = "/query-param")
	public String queryParam(@RequestParam Map<String, String> queryParam) {
		
		StringBuilder sb = new StringBuilder();
		
		queryParam.entrySet().forEach(entry -> {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
			System.out.println("\n");
			
			sb.append(entry.getKey()+" = "+entry.getValue()+"\n");
		});
		
		return sb.toString();
	}
	
	// Query Param 개별적으로 받는 방식
	@GetMapping("/query-param02")
	public String queryParam02(
			@RequestParam String name,
			@RequestParam String email,
			@RequestParam int age
	) {
		System.out.println(name);
		System.out.println(email);
		System.out.println(age);
		
		return name+" "+email+" "+age;
	}
	
	// http://localhost:9090/api/get/query-param?user={user}&email={email@example.com}&age={age}&address={address}
	// Query Param Custom Object 으로 받는 방식
	@GetMapping("/query-param03")
	public String queryParam03(UserRequest userRequest) {
		System.out.println(userRequest.getName());
		System.out.println(userRequest.getEmail());
		System.out.println(userRequest.getAge());
		
		return userRequest.toString();
	}
}
