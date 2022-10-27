package com.usuario.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.usuarios.service.models.Bike;

@FeignClient(name = "bike-service")
public interface BikeFeignClient {

	@PostMapping("/bike")
	public Bike save(@RequestBody Bike bike);
	
	@GetMapping("/bike/usuario/{userId}")
	public List<Bike> getBikes(@PathVariable ("userId") int userId);
	
}
