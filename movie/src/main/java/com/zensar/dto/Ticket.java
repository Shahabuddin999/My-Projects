package com.zensar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
	private int id;
	@NotBlank
	@Size(min = 10, max = 50, message="Title must be between 10 to 50 Chars")
	private String title; // Movie title
	@NotBlank
	@Size(min = 10, max = 50, message="Type must be between 10 to 50 Chars")
	private String type; // Balcony or Stalls
	@NotBlank
	private double unitPrice;
	@NotBlank
	private int noOfTickets;
	@NotBlank
	private double price;
}
