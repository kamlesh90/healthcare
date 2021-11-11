package in.nit.hc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nit.hc.entity.Appointment;
import in.nit.hc.entity.Doctor;
import in.nit.hc.entity.Specialization;
import in.nit.hc.service.IAppointmentService;
import in.nit.hc.service.IDoctorService;
import in.nit.hc.service.ISpecializationService;

@Controller
@RequestMapping("/appoint")
public class AppointmentController {
	
	@Autowired
	private IAppointmentService appointService;
	
	@Autowired
	private IDoctorService doctorService;
	
	@Autowired
	private ISpecializationService specService;
	
	private void createDynamicUi(Model model) {
		Map<Long, String> doctorMap = doctorService.getIdAndName(); 
		model.addAttribute("doctorMap",doctorMap);
	}
	
	@GetMapping("/register")
	public String showRegister(Model model) {
		createDynamicUi(model);
		
		return "AppointmentRegister";
	}
	
	@PostMapping("/save")
	public String saveAppointment(@ModelAttribute Appointment appointment, Model model) {
		Long id = appointService.saveAppointment(appointment);
		String message = "Appointment "+id+" created!!";
		model.addAttribute("message", message);
		model.addAttribute("appointment",new Appointment()) ;
		createDynamicUi(model); 
		
		return "AppointmentRegister";
	}

	@GetMapping("/all")
	public String getAllAppointments(Model model) {
		List<Appointment> appointsList = appointService.getAllAppointment();
		
		model.addAttribute("appointList", appointsList);
		
		return "AppointmentData";
	}
	
	@GetMapping("/edit")
	public String editAppointment(@RequestParam(value = "id", required = true) Long appId, Model model, RedirectAttributes attributes) {
		String page = null;
		try {

			Appointment appointment = appointService.getOneAppointment(appId);
			model.addAttribute("appointment", appointment);
			createDynamicUi(model); 
			
			page="AppointmentEdit";
			
		}catch (Exception e) {
			e.printStackTrace();
			attributes.addAttribute("msg", e.getMessage());
			
			page = "redirect:all";
			
		}
		
		return page;
	}
	
	@PostMapping("/update")
	public String updateAppointment(@ModelAttribute Appointment appointment, RedirectAttributes attributes) {
		appointService.updateAppointment(appointment); 
		attributes.addAttribute("msg1", "update success!!");
		
		return "redirect:all";
	}
	
	@GetMapping("/delete")
	public String deleteAppointment(@RequestParam(value = "id", required = true) Long id) {
		appointService.removeAppointment(id); 
		
		return "redirect:all";
	}
	
	@GetMapping("/view")
	public String viewAppointment(Model model) {
		Map<Long,String> specMap = specService.getSpecIdAndName();
		model.addAttribute("specMap", specMap);
		List<Doctor> docList = doctorService.getAllDoctors();
		model.addAttribute("docList", docList); 
		
		return "AppointmentSearch";
	}
	
	@GetMapping("/search")
	public String searchAppointment(@RequestParam(value = "specId", required = false, defaultValue = "0") Long sId, Model model) {
		List<Doctor> docList = doctorService.getDoctorBySpecName(sId);
		model.addAttribute("docList", docList);
		
		return "AppointmentSearch";
	}

}
































