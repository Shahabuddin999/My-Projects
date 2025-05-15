package com.webflux.demo.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
@Data
@Table("users")
public class User {
	
    @Id
    private Integer id;
    private String name;
    private String email;
}
