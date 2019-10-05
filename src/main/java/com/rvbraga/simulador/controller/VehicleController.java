package com.rvbraga.simulador.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rvbraga.simulador.domain.Vehicle;
import com.rvbraga.simulador.service.VehicleService;

@Controller
public class VehicleController {
	
	@Autowired
	VehicleService service;
	
	@GetMapping(value = "/main")
	public ModelAndView principal() {
		
		ModelAndView mv = new ModelAndView("/principal");
		
		return mv;
	}
	
	@GetMapping(value = "/envCord")
	public ModelAndView formEnviarCoordenadas(Vehicle vehicle) {
		
		System.out.println("Entrou no GET");
		ModelAndView mv = new ModelAndView("/enviarCoordenadas");		
		
		mv.addObject("vehicle", vehicle);
		
		return mv;
	}
	
	@PostMapping(value = "/envCord")
	public ModelAndView enviarCoordenadas(final Vehicle vehicle, BindingResult result) {
		
		
		System.out.println("Entrou no POST");
		if(result.hasErrors()) {
			System.out.println("Form possui erros");
			for(ObjectError erro :result.getAllErrors()) {
				System.out.println(erro.toString());
			}
			return formEnviarCoordenadas(vehicle);
		}
		ModelAndView mv = new ModelAndView("/enviarCoordenadas");		
		
		vehicle.setTime(LocalDateTime.now());
		
		String mensagem = service.enviar(vehicle);
		
		mv.addObject("mensagem", mensagem+" : "+LocalDateTime.now());
		
		return mv;
	}

}
