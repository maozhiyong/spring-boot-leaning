package com.neo.web;


import com.neo.model.User;
import com.neo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/getUsers")
	public List<User> getUsers() {
		List<User> users=userRepository.findAll();
		return users;
	}

	@RequestMapping("/addUser")
	public void addUser() {
        String string = UUID.randomUUID().toString();
        User user = new User();
		user.setId((long)new Random().nextInt(100));
		user.setUserName("max" + string);
		user.setEmail("max@qq.com" + string);
		user.setNickName("nick" + string);
		user.setRegTime("go");
		user.setPassWord("**");
		userRepository.save(user);
	}

}