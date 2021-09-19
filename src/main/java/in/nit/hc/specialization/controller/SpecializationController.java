package in.nit.hc.specialization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.nit.hc.specialization.entity.Specialization;
import in.nit.hc.specialization.service.ISpecializationService;

@Controller
@RequestMapping("/spec")
public class SpecializationController {
	
	@Autowired
	private ISpecializationService specService;
	
	@GetMapping("/spec-register")  													// spec/spec-register
	public String showSpecRegister() {
		
		return "SpecRegister";
	}
	
	@PostMapping("/save")														    // spec/save
	public String saveSpecialization(@ModelAttribute Specialization spec, Model model) {
		
		Long id = specService.saveSpecialization(spec);
		String message = "Specialization record with ID ("+id+") has been created remember ID for future refference";
		model.addAttribute("message", message);
		
		return "SpecRegister";
	}
}
