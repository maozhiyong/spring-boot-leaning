package com.neo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neo.model.User;
import com.neo.repository.UserRepository;

@RestController
@EnableCaching
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/insert")
    public void insert() {
	    User user = new User();
	    user.setRegTime("2018");
	    user.setEmail("126@qq.com");
	    user.setUserName("nick");
	    user.setId(1L);
	    user.setPassword("asf@");
	    user.setNickname("nick");

        userRepository.save(user);
    }

    @RequestMapping("/hello")
    @Cacheable(value="helloCache")
    public String hello(String name) {
    	System.out.println("没有走缓存！");
        return "hello "+name;
    }

    @RequestMapping("/condition")
    @Cacheable(value="condition",condition="#name.length() <= 4")
    public String condition(String name) {
        System.out.println("没有走缓存！");
        return "hello "+name;
    }
    
    @RequestMapping("/getUsers")
    @Cacheable(value="usersCache",key="#nickname",condition="#nickname.length() >= 6")
    public List<User> getUsers(String nickname) {
    	List<User> users=userRepository.findByNickname(nickname);
    	System.out.println("执行了数据库操作");
        return users;
    }

    @RequestMapping("/getPutUsers")
    @CachePut(value="usersCache",key="#nickname")
    public List<User> getPutUsers(String nickname) {
        List<User> users=userRepository.findByNickname(nickname);
        System.out.println("执行了数据库操作");
        return users;
    }

    @RequestMapping("/allEntries")
    @CacheEvict(value="usersCache", allEntries=true)
    public String  allEntries(String nickname) {
        String msg="执行了allEntries";
        System.out.println(msg);
        return msg;
    }

    @RequestMapping("/beforeInvocation")
    @CacheEvict(value="usersCache", allEntries=true, beforeInvocation=true)
    public void beforeInvocation() {
        throw new RuntimeException("test beforeInvocation");
    }
}