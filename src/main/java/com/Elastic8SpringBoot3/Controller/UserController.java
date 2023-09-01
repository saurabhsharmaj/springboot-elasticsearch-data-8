package com.Elastic8SpringBoot3.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Elastic8SpringBoot3.Model.User;
import com.Elastic8SpringBoot3.service.UserService;

@RestController
@RequestMapping("/apidata")
public class UserController {

	@Autowired
	public UserService userService;
	
	@GetMapping("/SayHello")
	public String sayHello() {
		return "Hello ";
	}
	

	@GetMapping("/getAllData")
	public Iterable<User> getAllData() {
		return userService.listAll();
	}

	@PostMapping("/createUser")
	public User createUser(@RequestBody User user) {
		return userService.save(user);
	}
	

}
