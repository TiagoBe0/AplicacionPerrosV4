package com.perrosv1.app.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.perrosv1.app.entidades.Perro;
import com.perrosv1.app.servicios.FotoServicio;
import com.perrosv1.app.servicios.PerroService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/perro") //localhost:8080/perro
public class PerroController {

	@Autowired
	private PerroService perroService;
	@Autowired
	private FotoServicio fotoServicio;
        
	@GetMapping("/registro") //localhost:8080/perro/registro
	public String formulario() {
		
		return "form-perro";
	}
		
	@PostMapping("/registro")
	public String guardar(ModelMap modelo, @RequestParam String nombre, @RequestParam String apodo,MultipartFile archivo, @RequestParam String raza ) {
		
		try {
			perroService.guardar(nombre, apodo, raza, archivo);
			
			modelo.put("exito", "Registro exitoso");
			
			return "form-perro";
			
			
			
		} catch (Exception e) {
			
			modelo.put("error", "Falto algun dato");
			return "form-perro";
		}
	}
	

	@GetMapping("/modificar/{id}") //PATHVARIABLE
	public String modificar(@PathVariable String id, ModelMap modelo ) {
            
		modelo.put("perro", perroService.getOne(id));
            
		return "form-perro-modif";
	}
	
	@PostMapping("/modificar/{id}")
	public String modificar(ModelMap modelo, @PathVariable String id, @RequestParam String nombre, @RequestParam String apodo, @RequestParam String foto, @RequestParam String raza ) {
		
		try {
			perroService.modificar(id,nombre, apodo, foto, raza);
			modelo.put("exito", "Modificacion exitosa");
			
			return "list-perro";
		} catch (Exception e) {
			modelo.put("error", "Falto algun dato");   
			return "form-perro-modif";
		}
	}
	
	
	@GetMapping("/lista")
	public String lista(ModelMap modelo) {
		
		List<Perro> perrosLista = perroService.listarTodos();
		
		modelo.addAttribute("perros",perrosLista);

		
		return "list-perro";
	}
                    @GetMapping("/listaGaleria")
	public String listaGaleria(ModelMap modelo) {
		
		List<Perro> perrosLista = perroService.listarTodos();
		
		modelo.addAttribute("perros",perrosLista);
                                                modelo.addAttribute("fotos",fotoServicio.listarTodas());

		
		return "galeria";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/baja/{id}")
	public String baja(@PathVariable String id) {
				
		try {
			perroService.baja(id);
			return "redirect:/perro/lista";
		} catch (Exception e) {
			return "redirect:/";
		}
		
	}
	
	@GetMapping("/alta/{id}")
	public String alta(@PathVariable String id) {
		
		try {
			perroService.alta(id);
			return "redirect:/perro/lista";
		} catch (Exception e) {
			return "redirect:/";
		}
	}
	
}
