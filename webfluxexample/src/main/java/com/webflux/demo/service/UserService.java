package com.webflux.demo.service;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webflux.demo.dto.Customer;
import com.webflux.demo.entity.Order;
import com.webflux.demo.entity.User;
import com.webflux.demo.entity.UserWithOrders;
import com.webflux.demo.repository.OrderRepository;
import com.webflux.demo.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

@Service
public class UserService {

	private final UserRepository userRepository;

	private OrderRepository orderRepository;

	public UserService(UserRepository userRepository, OrderRepository orderRepository) {
		this.userRepository = userRepository;
		this.orderRepository = orderRepository;
	}

	public Flux<User> getAllUsers() {
		// return userRepository.findAll() its working fine with asynchronos way. but if
		// you want delay for each record then written below code
		return userRepository.findAll().delayElements(Duration.ofSeconds(1))
				.doOnNext(user -> System.out.println("processing user: " + user)).map(user -> user);
	}

	public Mono<User> getUserById(Integer id) {
		return userRepository.findById(id);
	}

	public Mono<User> createUser(User user) {
		return userRepository.save(user);
	}

	public Mono<Order> createOrder(Order order) {
		return orderRepository.save(order);
	}

	public Mono<User> updateUser(Integer id, User user) {
		return userRepository.findById(id).flatMap(existing -> {
			existing.setName(user.getName());
			existing.setEmail(user.getEmail());
			return userRepository.save(existing);
		});
	}

	public Mono<Void> deleteUser(Integer id) {
		return userRepository.deleteById(id);
	}

	public Flux<Customer> getAllCustomerUsingFlux() {

		Flux<Customer> flux = Flux.range(1, 10).delayElements(Duration.ofSeconds(1))
				.doOnNext(i -> System.out.println("processing count:" + i))
				.map(i -> new Customer(i, "user " + i, "user " + i + "@gmail.com"));
		return flux;
	}

	public Mono<Tuple2<Long, List<User>>> getUsersAndCount() {
		return userRepository.findAll().collectList().map(list -> Tuples.of((long) list.size(), list));
	}

	public Mono<UserWithOrders> getUserWithOrders(Integer userId) {
		Mono<User> userMono = userRepository.findById(userId);
		Flux<Order> orderFlux = orderRepository.findByUserId(userId);

		return userMono.zipWith(orderFlux.collectList()).map(tuple -> new UserWithOrders(tuple.getT1(), tuple.getT2()));
	}

	public Flux<UserWithOrders> getAllUsersWithOrders() {
	    return userRepository.findAll()
	        .flatMap(user -> orderRepository.findByUserId(user.getId())
	            .collectList()
	            .map(orders -> {
	                UserWithOrders userWithOrders = new UserWithOrders();
	                userWithOrders.setUser(user);
	                userWithOrders.setOrders(orders);
	                return userWithOrders;  // Return the combined object here
	            })
	        );
	}
}
