package com.bike.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bike.service.entities.Bike;
import com.bike.service.repository.BikeRepository;


@Service
public class BikeService {

	@Autowired
	private BikeRepository bikeRepository;
	
	public List<Bike> getAll() {
		return bikeRepository.findAll();
	}
	
	public Bike getBikeById(int id) {
		return bikeRepository.findById(id).orElse(null);
	}
	
	public Bike save(Bike bike) {
		Bike newBike = bikeRepository.save(bike);
		return newBike;
	}
	
	public List<Bike> getBikeByUserId(int userId){
		return bikeRepository.findByUserId(userId);
	}
	
}
