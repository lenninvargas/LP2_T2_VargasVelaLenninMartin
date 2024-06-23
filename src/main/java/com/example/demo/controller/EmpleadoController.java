package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.AreaEntity;
import com.example.demo.entity.EmpleadoEntity;
import com.example.demo.repository.AreaRepository;
import com.example.demo.repository.EmpleadoRepository;

@Controller
public class EmpleadoController{
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private AreaRepository areaRepository;
	
	@GetMapping("/")
	public String home(Model model) {
		List<EmpleadoEntity> listaEmpleado = empleadoRepository.findAll();
		model.addAttribute("listaEmpleado", listaEmpleado);
		return "home";
	}
	
	@GetMapping("/registrar_empleado")
	public String showRegistrarEmpleado(Model model) {
		List<AreaEntity> listaArea = areaRepository.findAll();
		model.addAttribute("listaArea", listaArea);
		model.addAttribute("empleado", new EmpleadoEntity());
		return "registrar_empleado";
	}
	
	@PostMapping("/registrar_empleado")
	public String registrarEmpleado(Model model, @ModelAttribute EmpleadoEntity empleado) {
		if(empleadoRepository.findByDniEmpleado(empleado.getDniEmpleado()) != null) {
			model.addAttribute("errorMessage", "¡Error! DNI en uso");
			model.addAttribute("empleado", new EmpleadoEntity());
			return "registrar_empleado"; 
		}
		empleadoRepository.save(empleado);
		return "redirect:/";
	}
}
