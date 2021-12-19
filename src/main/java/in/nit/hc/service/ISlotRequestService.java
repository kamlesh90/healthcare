package in.nit.hc.service;

import java.util.List;

import in.nit.hc.entity.SlotRequest;


public interface ISlotRequestService {
	
	//patient book the slot
	Long saveSlotRequest(SlotRequest sr);
	
	//admin view all request
	List<SlotRequest> getAllSlotRequests();
	
	SlotRequest getOneSlotRequest(Long id);
	// admin/patient can update status
	void updateSlotRequestStatus(Long id, String status);
	
	List<SlotRequest> getAllSlotsByPatientEmail(String email);
	
	List<SlotRequest> getAllSlotsByDoctorEmail(String email);
	
	List<Object[]> getStatusAndStatusCount();
	
	
}
