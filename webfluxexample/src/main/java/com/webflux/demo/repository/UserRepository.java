package com.webflux.demo.repository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.webflux.demo.entity.User;

public interface UserRepository extends ReactiveCrudRepository<User, Integer> {
}
