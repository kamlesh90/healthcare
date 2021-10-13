package in.nit.hc.appointment.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.nit.hc.appointment.service.IAppointmentService;
import in.nit.hc.doctor.service.IDoctorService;

@Controller
@RequestMapping("/appoint")
public class AppointmentController {
	
	@Autowired
	private IAppointmentService appointService;
	
	@Autowired
	private IDoctorService doctorService;
	
	@GetMapping("/register")
	public String showRegister(Model model) {
		createDynamicUi(model);
		
		return "AppointmentRegister";
	}
	
	private void createDynamicUi(Model model) {
		Map<Long, String> doctorMap = doctorService.getIdAndName(); 
		model.addAttribute("doctorMap",doctorMap);
	}
}
