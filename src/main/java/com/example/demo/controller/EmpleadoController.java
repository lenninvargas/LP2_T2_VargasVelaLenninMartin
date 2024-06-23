package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
			List<AreaEntity> listaArea = areaRepository.findAll();
			model.addAttribute("listaArea", listaArea);
			model.addAttribute("empleado", new EmpleadoEntity());
			return "registrar_empleado"; 
		}
		empleadoRepository.save(empleado);
		return "redirect:/";
	}
	
	@GetMapping("/detalle_empleado/{id}")
	public String showDetalleEmpleado(Model model, @PathVariable("id")String id) {
		EmpleadoEntity empleado = empleadoRepository.findById(id).get();
		model.addAttribute("empleado", empleado);
		return "detalle_empleado";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminarEmpleado(@PathVariable("id")String id) {
		empleadoRepository.deleteById(id);
		return "redirect:/";
	}
	
	@GetMapping("/editar_empleado/{id}")
	public String showEditarEmpleado(Model model, @PathVariable("id")String id) {
		EmpleadoEntity empleado = empleadoRepository.findById(id).get();
		List<AreaEntity> listaArea = areaRepository.findAll();
		model.addAttribute("listaArea", listaArea);
		model.addAttribute("empleado", empleado);
		return "editar_empleado";
	}
	
	@PostMapping("/editar_empleado/{id}")
	public String editarEmpleado(@ModelAttribute EmpleadoEntity empleado, @PathVariable("id")String id) {
		EmpleadoEntity empleadoEncontrado = empleadoRepository.findById(id).get();
		empleadoEncontrado.setNombreEmpleado(empleado.getNombreEmpleado());
		empleadoEncontrado.setApellidoEmpleado(empleado.getApellidoEmpleado());
		empleadoEncontrado.setFechaNacimiento(empleado.getFechaNacimiento());
		empleadoEncontrado.setDireccion(empleado.getDireccion());
		empleadoEncontrado.setCorreo(empleado.getCorreo());
		empleadoEncontrado.setAreaId(empleado.getAreaId());
		empleadoRepository.save(empleadoEncontrado);
		return "redirect:/";
	}

}
