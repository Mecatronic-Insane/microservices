package com.car.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.car.service.entities.Car;

@Repository
@EnableJpaRepositories
public interface CarRepository extends JpaRepository<Car, Integer> {

	List<Car> findByUserId(int userId);
	
}
