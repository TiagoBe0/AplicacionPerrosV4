package com.perrosv1.app.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.perrosv1.app.entidades.Perro;
import com.perrosv1.app.servicios.PerroService;

@Controller 
@RequestMapping("/") //localhost:8080/
public class MainController {
	

	@GetMapping("/")//localhost:8080/
	public String index() {
		
		
	return "index";
	}
	
}
