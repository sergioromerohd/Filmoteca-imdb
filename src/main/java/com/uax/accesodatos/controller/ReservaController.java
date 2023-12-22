package com.uax.accesodatos.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uax.accesodatos.dto.ReservaDto;
import com.uax.accesodatos.services.PeliculaService;
import com.uax.accesodatos.services.ReservaService;
import com.uax.accesodatos.services.TrailerService;

import jakarta.servlet.http.HttpServletRequest;

import com.uax.accesodatos.services.ReservaService;

@Controller
public class ReservaController {

	@Autowired
	ReservaService reservaService;
	
	@Autowired
	PeliculaService peliculaService;
	

	// Obtener vista Reserva
	@GetMapping("/reserva")
	public String getReserva(Model model, HttpServletRequest http) {
		
		String username = http.getUserPrincipal().getName();// Obtener el usuario
		
		 
		model.addAttribute("userName", http.getUserPrincipal().getName());
		model.addAttribute("reservas", reservaService.listaReservaService(username)); // Obtener todas las reservas de un usuario
		
		return "/views/reservas";
	}
	
	// Eliminar la reserva por usuario e id pelicula
	@PostMapping("/eliminarReserva")
	public String deleteReserva(@RequestParam("username") String username, @RequestParam("id") String id) {
	
		reservaService.deleteReservaService(username, id);
		
		return "/views/reservas";
	}
	
	// Add reserva por usuario de pelicula
	@GetMapping("/addReserva")
	public String addReserva(HttpServletRequest http, @RequestParam("id") String id) {
		
		// Variables
		ReservaDto reserva = new ReservaDto();
		double precio = Math.random()*2+1; // Precio random de la pelicula
		
		// Variables de fechas
		DateTimeFormatter fecha = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime fechFin = now.plusDays(3);
		
		// Insertar atributos
		reserva.setUsuario(http.getUserPrincipal().getName());
		reserva.setId(id);
		reserva.setFech_ini(fecha.format(now));
		reserva.setFech_fin(fecha.format(now.plusDays(3)));
		reserva.setPrecio(3);
		reserva.setEstado("SIN ENTRGAR");
		reserva.setPagado("PENDIENTE");
		
		// Llamada al service	
		peliculaService.getResponseById(id); // Insertar pelicula en bbd
		reservaService.addReservaService(reserva); // Insertar reserva
		
		
		return "redirect:/reserva";
	}
	
	
}
