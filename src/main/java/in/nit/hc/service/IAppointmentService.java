package in.nit.hc.service;

import java.util.List;

import in.nit.hc.entity.Appointment;

public interface IAppointmentService {
	
	public Long saveAppointment(Appointment appointment);
	
	public List<Appointment> getAllAppointment();
	
	public void updateAppointment(Appointment appointment);
	
	public void removeAppointment(Long id);
	
	public Appointment getOneAppointment(Long id);
}
