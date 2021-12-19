package in.nit.hc.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import in.nit.hc.constants.SlotStatus;
import in.nit.hc.entity.Appointment;
import in.nit.hc.entity.Patient;
import in.nit.hc.entity.SlotRequest;
import in.nit.hc.entity.User;
import in.nit.hc.service.IAppointmentService;
import in.nit.hc.service.IPatientService;
import in.nit.hc.service.ISlotRequestService;
import in.nit.hc.view.InvoicePdfView;

@Controller
@RequestMapping("/slots")
public class SlotRequestController {
	
	@Autowired
	private ISlotRequestService slotReqService;
	
	@Autowired
	private IAppointmentService appointService;
	
	@Autowired
	private IPatientService patientService;
	
	@GetMapping("/book")
	public String bookSlotReq(@RequestParam Long appntId, Model model, HttpSession session) {
		
		Appointment appointment = appointService.getOneAppointment(appntId);
		
		// for patient
		User user = (User) session.getAttribute("userOb");
		String email = user.getUserName();
		Patient patient = patientService.getPatientByEmail(email);
		
		// create SLOT Req
		
		SlotRequest sr = new SlotRequest();
		sr.setAppointment(appointment);
		sr.setPatient(patient);
		sr.setStatus(SlotStatus.PENDING.name());
		
		try {
			
			slotReqService.saveSlotRequest(sr);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
			String appointDt = sdf.format(appointment.getAppointmentDt());
			
			String message = " Patient : "+patient.getFirstName()+" "+patient.getLastName()+", "+
							 " Request for Doctor : "+appointment.getDoctor().getFirstName()+" "+appointment.getDoctor().getLastName()+", "+
							 " On Date : "+appointDt+", "+
							 " Submit with Status : "+sr.getStatus() ;
			
			model.addAttribute("message", message);
							
		
		} catch (Exception e) {
			
			e.printStackTrace();
			model.addAttribute("message", "BOOKING REQUEST ALREADY MADE FOR THIS APPOINTMENT/DATE");
		}

		return "SlotRequestMesage";
	}
	
	@GetMapping("/all")
	public String getAllSlotReq(Model model) {
		List<SlotRequest> list = slotReqService.getAllSlotRequests();
		model.addAttribute("list", list);
		
		return "SlotReqData";
	}
	
	@GetMapping("/accept")
	public String updateSlotAccept(@RequestParam(value = "id",required = true) Long id) {
		slotReqService.updateSlotRequestStatus(id, SlotStatus.ACCEPTED.name());
		SlotRequest sr = slotReqService.getOneSlotRequest(id);
		
		if(sr.getStatus().equals(SlotStatus.ACCEPTED.name())) { 
			appointService.updateSlotCountForAppointment(sr.getAppointment().getId(), -1); 
		}
		
		return "redirect:all";
	}

	@GetMapping("/reject")
	public String updateSlotReject(@RequestParam(value = "id",required = true) Long id) {
		slotReqService.updateSlotRequestStatus(id, SlotStatus.REJECTED.name()); 
		
		return "redirect:all";
	}
	
	@GetMapping("/patient-slots")
	public String viewMyPatSlots(Principal p, Model model) {
		String email = p.getName();
		List<SlotRequest> list = slotReqService.getAllSlotsByPatientEmail(email);
		model.addAttribute("list", list);
		
		return "PatientSlotReqData";
	}
	
	@GetMapping("/cancel")
	public String updateSlotCancel(@RequestParam(value = "id", required = true) Long id) {
		 SlotRequest sr = slotReqService.getOneSlotRequest(id);
		 Appointment app = sr.getAppointment();

		 if(sr.getStatus().equals(SlotStatus.ACCEPTED.name())) {
			 slotReqService.updateSlotRequestStatus(id, SlotStatus.CANCELLED.name());
			 appointService.updateSlotCountForAppointment(app.getId(), 1); 
		 }
		
		return "redirect:patient-slots";
	}
	
	@GetMapping("/doc-slots")
	public String viewMyDocSlots(Principal p, Model model) {
		String docEmail = p.getName();
		List<SlotRequest> list = slotReqService.getAllSlotsByDoctorEmail(docEmail);
		model.addAttribute("list", list);
		 
		return "DoctorSlotReqData";
	}
	
	@GetMapping("/pdf")
	public ModelAndView getInvoice(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView();
 		mv.setView(new InvoicePdfView()); 
		
		SlotRequest slotReq = slotReqService.getOneSlotRequest(id);
		mv.addObject("slotReq",slotReq);
		
		return mv;
	}
}










































