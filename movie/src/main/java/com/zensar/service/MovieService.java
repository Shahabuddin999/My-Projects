package com.zensar.service;

import java.util.List;

import com.zensar.dto.Movie;
import com.zensar.dto.Ticket;

public interface MovieService {

	public Movie createMovie(Movie movie);

	public Movie getMovieById(int movieId);

	public List<Ticket> getTicketsByMovieId(int movieId);
}