package com.webflux.demo.controller;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webflux.demo.dto.Customer;
import com.webflux.demo.entity.User;
import com.webflux.demo.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public Flux<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Mono<User> getUser(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public Mono<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public Mono<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable("id") Integer id) {
        return userService.deleteUser(id);
    }
    
    // "produces = MediaType.TEXT_EVENT_STREAM_VALUE" is important to work with Flux else will not work
    @GetMapping(value="/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE) 
    public Flux<Customer> getAllCustomerUsingFlux() {
        return userService.getAllCustomerUsingFlux();
    }
}
