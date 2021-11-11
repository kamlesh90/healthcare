package in.nit.hc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nit.hc.entity.Doctor;
import in.nit.hc.service.IDoctorService;
import in.nit.hc.service.ISpecializationService;
import in.nit.hc.service.IUserService;
import in.nit.hc.util.EmailUtil;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private IDoctorService doctorService;
	
	@Autowired
	private ISpecializationService specService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private EmailUtil emailUtil;
	
	/**
	 * 	Define one method 
	 */
	private void createDynamicCommonUi(Model model) {
		model.addAttribute("specialization", specService.getSpecIdAndName());
	}
	
	@GetMapping("/register")
	public String showRegister(Model model) {                               
		createDynamicCommonUi(model); 			// call createDynamicCommonUi
		
		return "DoctorRegister";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute Doctor doctor, Model model) {
		
		Long id = doctorService.saveDoctor(doctor);
		String message = "Doctor ("+id+") created";
		model.addAttribute("message", message);
		
		return "redirect:register"; 
	}
	
	@GetMapping("/all")
	public String getAllDoctors(Model model) {
		
		List<Doctor> docList = doctorService.getAllDoctors();
		model.addAttribute("docList", docList);
		
		return "DoctorData";
	}
	
	@GetMapping("/delete")
	public String remove(Long id, RedirectAttributes attributes) {
		
		String message = null;
		
		try {
			doctorService.removeDoctor(id); 
			message = "Doctor removed"; 
		}catch(RuntimeException re) {
			re.printStackTrace();
			message = re.getMessage();
		}
		
		attributes.addAttribute("message", message);
		
		return "redirect:all";
	}
	
	@PostMapping("/update")
	public String update(Doctor doctor, RedirectAttributes attributes) {
		
		doctorService.updateDoctor(doctor); 
		String message = "(+id+) has been deleted";
		attributes.addAttribute("message", message);
		
		return "redirect:all";
	}
	
	@GetMapping("/edit")
	public String showEditPage(Long id, Model model, RedirectAttributes attributes) {
		
		String page = null;
		
		try {
			Doctor doctor = doctorService.getOneDoctor(id);
			model.addAttribute("doctor", doctor);
			createDynamicCommonUi(model); 
			page = "DoctorEdit";
					
		} catch (RuntimeException re) {
			re.printStackTrace();
			attributes.addAttribute("message", re.getMessage());
			
			page = "redirect:all";
		}
		
		return page;
	}
		
}	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

