package com.usuario.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.service.entities.User;
import com.usuario.service.repository.UserRepository;
import com.usuarios.service.models.Bike;
import com.usuarios.service.models.Car;

@Service
public class UserService {
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UserRepository userRepository;
	
	
	public List<Car> getCars(int userId){
		@SuppressWarnings("unchecked")
		List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/user/"+ userId, List.class);
		return cars;
	}
	
	public List<Bike> getBikes(int userId){
		@SuppressWarnings("unchecked")
		List<Bike> bikes = restTemplate.getForObject("http://localhost:8003/bike/user/"+ userId, List.class);
		return bikes;
	}
	
	
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
	public User getUserById(int id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public User save(User user) {
		User newUser = userRepository.save(user);
		return newUser;
	}
}
