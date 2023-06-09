package com.Elastic8SpringBoot3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Elastic8SpringBoot3.Model.User;
import com.Elastic8SpringBoot3.Repository.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Iterable<User> listAll() {

		return (List<User>) userRepository.findAll();
	}

	public User save(User user) {
		return userRepository.save(user);
	}

}
