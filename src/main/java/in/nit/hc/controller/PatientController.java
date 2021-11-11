package in.nit.hc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nit.hc.entity.Patient;
import in.nit.hc.exception.PatientNotFoundException;
import in.nit.hc.service.IPatientService;

@Controller
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private IPatientService patientService;
	
	@GetMapping("/patient-register")
	public String registerPatient() {
		
		return "PatientRegister";
	}
	
	@PostMapping("/save")
	public String savePatient(@ModelAttribute Patient patient, Model model) {

		Long id = patientService.savePatient(patient);
		String message = "Patient ("+id+") added";
		model.addAttribute("message", message);
		
		return "redirect:patient-register";
	}
	
	@GetMapping("/all")
	public String getAllPatient(Model model) {
		List<Patient> list = patientService.getAllPatient();
		model.addAttribute("list", list);
		
		return "PatientData";
	}
	
	@GetMapping("/edit")
	public String getOnePatient(@RequestParam Long id, Model model, RedirectAttributes attributes) {
		
		String page="";
		
		try {
			Patient patient = patientService.getOnePatient(id);
			model.addAttribute("patient", patient);
			page="PatientEdit";
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page="redirect:all";
		}
		return page;
	}

	@PostMapping("/update")
	public String updatePatient(@ModelAttribute Patient patient, RedirectAttributes attributes) {
		
		patientService.updatePatient(patient); 
		attributes.addAttribute("message", "Patient ("+patient.getId()+") has been updated !!");
		
		return "redirect:all";
	}
	
	@GetMapping("/delete")
	public String removePatient(@RequestParam Long id, RedirectAttributes attributes) {
		
		try {
			patientService.deletePatient(id); 
			attributes.addAttribute("message", "patient has been removed !!");
			
		} catch (PatientNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
		}
		
		return "redirect:all";
	}
	
	
	
	
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
