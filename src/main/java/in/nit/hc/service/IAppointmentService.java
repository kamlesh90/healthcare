package in.nit.hc.service;

import java.util.List;
import java.util.Map;

import in.nit.hc.entity.Appointment;

public interface IAppointmentService {
	
	public Long saveAppointment(Appointment appointment);
	public void updateAppointment(Appointment appointment);
	public void removeAppointment(Long id);
	public Appointment getOneAppointment(Long id);
	public List<Appointment> getAllAppointment();
	
	List<Object[]> getAppoinmentsByDoctor(Long docId);
	void updateSlotCountForAppointment(Long appId, int count);
	List<Object[]> getAppointmentsByDocEmail(String email);
	
	long getAppointmentCount();
}
