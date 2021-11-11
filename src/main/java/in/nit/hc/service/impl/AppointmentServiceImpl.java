package in.nit.hc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import in.nit.hc.entity.Appointment;
import in.nit.hc.exception.AppointmentNotFoundException;
import in.nit.hc.repository.AppointmentRepository;
import in.nit.hc.service.IAppointmentService;
import in.nit.hc.util.EmailUtil;

@Service
public class AppointmentServiceImpl implements IAppointmentService{

	@Autowired
	private AppointmentRepository appointRepository;
	
	@Autowired
	private EmailUtil mailUtil;
	
	@Override
	public Long saveAppointment(Appointment appointment) {
		Long appointmentId = appointRepository.save(appointment).getId();
	
		return appointmentId;
	}

	@Override
	public List<Appointment> getAllAppointment() {
		List<Appointment> appointsList = appointRepository.findAll();
		
		return appointsList;
	}

	@Override
	public void updateAppointment(Appointment appointment) {
		
		appointRepository.save(appointment);
	}

	@Override
	public void removeAppointment(Long id) {
		Appointment appointment = getOneAppointment(id);
		appointRepository.delete(appointment); 
	}

	@Override
	public Appointment getOneAppointment(Long id) {
/*		Appointment appointment=null;
		Optional<Appointment> opt = appointRepository.findById(id);
		
		if(opt.isPresent()) {
			appointment=opt.get();
		}else {
			opt.orElseThrow(
								() -> new AppointmentNotFoundException("Appointment "+id+" not available!!")
						   );
		}
*/		
		return appointRepository.findById(id).orElseThrow(
															() -> new AppointmentNotFoundException("Appointment "+id+" not available!!")  
														 );
	}
	
}
































