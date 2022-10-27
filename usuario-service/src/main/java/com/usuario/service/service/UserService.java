package com.usuario.service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.service.entities.User;
import com.usuario.service.feignclients.BikeFeignClient;
import com.usuario.service.feignclients.CarFeignClient;
import com.usuario.service.repository.UserRepository;
import com.usuarios.service.models.Bike;
import com.usuarios.service.models.Car;

@Service
public class UserService {
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CarFeignClient carFeignClient;
	
	@Autowired
	private BikeFeignClient bikeFeignClient;
	
	//Rest Template
	public List<Car> getCars(int userId){
		@SuppressWarnings("unchecked")
		List<Car> cars = restTemplate.getForObject("http://carro-service/car/user/"+ userId, List.class);
		return cars;
	}
	
	public List<Bike> getBikes(int userId){
		@SuppressWarnings("unchecked")
		List<Bike> bikes = restTemplate.getForObject("http://moto-service/bike/user/"+ userId, List.class);
		return bikes;
	}
	
	//FeignClient
	public Car saveCar(int userId,Car car) {
		car.setUserId(userId);
		Car newCar = carFeignClient.save(car);
		return newCar;
	}
	
	public Bike saveBike(int userId,Bike bike) {
		bike.setUserId(userId);
		Bike newBike = bikeFeignClient.save(bike);
		return newBike;
	}
	
	public Map<String, Object> getUserAndVehicles(int userId){
		Map<String,Object> result = new HashMap<>();
		User user = userRepository.findById(userId).orElse(null);
		
		if(user == null) {
			result.put("Message", "The user doesn't exist");
			return result;
		}
		
		result.put("User", user);
		
		List<Car> cars = carFeignClient.getCars(userId);
		if(cars.isEmpty()) {
			result.put("Cars", "The user haven't cars");
			return result;
		}else {
			result.put("Cars", cars);
		}
		
		List<Bike> bikes = bikeFeignClient.getBikes(userId);
		if(bikes.isEmpty()) {
			result.put("Bikes", "The user haven't bikes");
			return result;
		}else {
			result.put("Bikes", bikes);
		}
		
		return result;
		
	}
	
	
	//common
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
