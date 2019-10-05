package com.rvbraga.simulador.service;

import java.net.URI;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rvbraga.simulador.domain.Vehicle;

@Service
public class VehicleService {

	public String enviar(Vehicle vehicle) {

		RestTemplate restTemplate = new RestTemplate();
		
		System.out.println("Enviando Coordenadas do veiculo :"+vehicle.getId());
		System.out.println("Latitude :"+vehicle.getLat());
		System.out.println("Longitude :"+vehicle.getLgn());
		System.out.println("Time :"+vehicle.getTime());
		try {
			
			RequestEntity<Vehicle> request = RequestEntity.post(URI.create("http://localhost:8081/ReceiveCoordinate")).body(vehicle);

			ResponseEntity<String> response = restTemplate.exchange(request, String.class);

			return response.getBody();
			
		} catch (Exception e) {
			
			return e.getMessage();
			
		}

	}
}
