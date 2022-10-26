package com.usuario.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.usuarios.service.models.Car;

@FeignClient(name = "car-service")
public interface CarFeignClient {
	
	@PostMapping("/car")
	public Car save(@RequestBody Car car);
	
	@GetMapping("/car/user/{userId}")
	public List<Car> getCars(@PathVariable ("userId") int userId);

}
