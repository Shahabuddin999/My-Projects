package com.webflux.demo.service;
import java.time.Duration;

import org.springframework.stereotype.Service;

import com.webflux.demo.dto.Customer;
import com.webflux.demo.entity.User;
import com.webflux.demo.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
	
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public Mono<User> createUser(User user) {
        return userRepository.save(user);
    }

    public Mono<User> updateUser(Integer id, User user) {
        return userRepository.findById(id)
            .flatMap(existing -> {
                existing.setName(user.getName());
                existing.setEmail(user.getEmail());
                return userRepository.save(existing);
            });
    }

    public Mono<Void> deleteUser(Integer id) {
        return userRepository.deleteById(id);
    }
    
    public Flux<Customer> getAllCustomerUsingFlux() {
    	
    	Flux<Customer> flux = Flux.range(1, 10)  
		    			 .delayElements (Duration.ofSeconds(1))  
		    			 .doOnNext(i -> System.out.println("processing count:" + i))  
		    			 .map(i -> new Customer(i, "user "+i, "user "+i+"@gmail.com"));
    	return flux;
    }
}
