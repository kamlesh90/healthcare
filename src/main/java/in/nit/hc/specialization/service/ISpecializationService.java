package in.nit.hc.specialization.service;

import java.util.List;
import java.util.Map;

import in.nit.hc.specialization.entity.Specialization;

public interface ISpecializationService {
	
	public Long saveSpecialization(Specialization spec); // for save 
	public List<Specialization> getAllSpecializations(); // getAll 
	public void removeSpecialization(Long id);			 // deleteById
	public void updateSpecialization(Specialization spec);	// update /Edit url 
	public Specialization getOneSpecialization(Long id);	// getOne 
	
	public Boolean isSpecCodeExist(String specCode);  			// to perform count operation in repo to avoid duplicate entry in DB
	public Boolean isSpecCodeExist(String specCode, Long id);	// to resolve edit bug in case of edit no need to check with current row, because it is already there in DB 
																// so other then current row we need to check others row 
								
	public Map<Long,String> getSpecIdAndName();				// to perform module integration integrate Specialization with Doctor module show what Specialization Doctor has 
															// and id for internal code mapping 
	
}
