package in.nit.hc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.nit.hc.entity.SlotRequest;

public interface SlotRequestRepository extends JpaRepository<SlotRequest, Long>{
	
	@Modifying
	@Query("UPDATE SlotRequest SET status=:status WHERE id=:id")
	void updateSlotRequestStatus(Long id, String status);
	
	@Query("SELECT sr FROM SlotRequest sr INNER JOIN sr.patient as p WHERE p.email=:patEmail")
  	List<SlotRequest> getSlotsByPatientEmail(String patEmail); 
	
	@Query("SELECT status, COUNT(status) FROM SlotRequest GROUP BY status")
	List<Object[]> getSlotStatusAndStatusCount(); 

	@Query("SELECT sr FROM SlotRequest sr INNER JOIN sr.appointment as a INNER JOIN a.doctor as d WHERE d.email=:docEmail AND sr.status=:status")
  	List<SlotRequest> getSlotsByDoctorEmail(String docEmail, String status);
}
