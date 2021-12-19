package in.nit.hc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.nit.hc.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
	
	@Query("SELECT apt.appointmentDt, apt.noOfSlots, apt.fees, apt.id FROM Appointment apt INNER JOIN apt.doctor as doc WHERE doc.id=:docId AND apt.noOfSlots>0 AND apt.appointmentDt>=current_date")                                      
	List<Object[]> getAppoinmentsByDoctor(Long docId);     
	
	@Modifying
	@Query("UPDATE Appointment SET noOfSlots = noOfSlots + :count WHERE id=:id")
	void updateSlotCountForAppointment(Long id, int count);
	
	@Query("SELECT apt.appointmentDt, apt.noOfSlots, apt.fees, apt.id FROM Appointment apt INNER JOIN apt.doctor as doc WHERE doc.email=:email AND apt.noOfSlots>0")
	List<Object[]> getAppoinmentsByDoctorEmail(String email);  
	
} 
