package com.formacionspring.appwebmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.formacionspring.appwebmvc.entity.Departamento;
import com.formacionspring.appwebmvc.service.DepartamentoService;

@Controller
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService servicio;
	
	@GetMapping({"/departamentos", "/"})
	public String listarDepartamentos(Model model) {
		model.addAttribute("dato", servicio.listarTodosLosDepartamentos());
		return "departamentos";
	}
	
	@GetMapping("/departamentos/nuevo")
	public String formularioDepartamento(Model modelo) {
		Departamento newDepartamento = new Departamento();
		modelo.addAttribute("departamentoKey", newDepartamento);
		return "nuevo_departamento";
	}
	
	@PostMapping("/departamentos")
	public String guardarDepartamento(@ModelAttribute("departamentos") Departamento departamento) {
		servicio.guardarDepartamento(departamento);
		return "redirect:/departamentos";
	}
	
	@GetMapping("/departamentos/editar/{id}")
	public String formularioEdicionDepartamento(@PathVariable Long id, Model modelo) {
		modelo.addAttribute("departamentoKey", servicio.obtenerDepartamentoPorId(id));
		return "editar_departamentos";
	}
	
	@PostMapping("/departamentos/editar/{id}")
	public String editarDepartamento(@PathVariable Long id, @ModelAttribute("departamentoKey") Departamento departamento) {
		Departamento departamentoEdit = servicio.obtenerDepartamentoPorId(id);
		departamentoEdit.setNombre(departamento.getNombre());
	
		servicio.guardarDepartamento(departamentoEdit);
		return "redirect:/departamentos";
	}
	
	@GetMapping("/departamentos/borrar/{id}")
	public String eliminarDepartamento(@PathVariable Long id) {
		servicio.eliminarDepartamento(id);
		return "redirect:/departamentos";
	}
}







