package in.nit.hc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nit.hc.constants.SlotStatus;
import in.nit.hc.entity.SlotRequest;
import in.nit.hc.exception.SlotReqNotfoundException;
import in.nit.hc.repository.SlotRequestRepository;
import in.nit.hc.service.ISlotRequestService;

@Service
public class SlotRequestServiceImpl implements ISlotRequestService {

	@Autowired
	private SlotRequestRepository slotReqRepo;
	
	public Long saveSlotRequest(SlotRequest sr) {

		return slotReqRepo.save(sr).getId(); 
	}

	
	public List<SlotRequest> getAllSlotRequests() {

		return slotReqRepo.findAll();
	}

	
	public SlotRequest getOneSlotRequest(Long id) {
/*		
		Optional<SlotRequest> opt = slotReqRepo.findById(id);
		SlotRequest sr = null;
		if(opt.isPresent()) {
			sr=opt.get();
		}else{
			
			throw new SlotReqNotfoundException("SLOT NOT AVAILABLE CHOOSE OTHER SLOT !!");
		}
*/		
		return slotReqRepo.findById(id)
				.orElseThrow(
						() -> new SlotReqNotfoundException("SLOT NOT AVAILABLE CHOOSE OTHER SLOT !!") 
							) 
				;
	}
	
	@Transactional
	public void updateSlotRequestStatus(Long id, String status) {
		slotReqRepo.updateSlotRequestStatus(id, status);
	}
	
	public List<SlotRequest> getAllSlotsByPatientEmail(String email) {
		
		return slotReqRepo.getSlotsByPatientEmail(email) ; 
	}

	public List<Object[]> getStatusAndStatusCount() {
		
		return slotReqRepo.getSlotStatusAndStatusCount();
		
	}

	public List<SlotRequest> getAllSlotsByDoctorEmail(String email) {
		
		return slotReqRepo.getSlotsByDoctorEmail(email,SlotStatus.ACCEPTED.name());
	}
}


































