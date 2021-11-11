package in.nit.hc.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import in.nit.hc.entity.Specialization;

public interface ISpecializationService {
	
	 Long saveSpecialization(Specialization spec); // for save 
	 List<Specialization> getAllSpecializations(); // getAll 
	 void removeSpecialization(Long id);			 // deleteById
	 void updateSpecialization(Specialization spec);	// update /Edit url 
	 Specialization getOneSpecialization(Long id);	// getOne 
	
	 Boolean isSpecCodeExist(String specCode);  			// to perform count operation in repo to avoid duplicate entry in DB
	 Boolean isSpecCodeExist(String specCode, Long id);	// to resolve edit bug in case of edit no need to check with current row, because it is already there in DB 
																// so other then current row we need to check others row 
								
	 Map<Long,String> getSpecIdAndName();				// to perform module integration integrate Specialization with Doctor module show what Specialization Doctor has 
															// and id for internal code mapping 
	
	 Page<Specialization> getAllSpecializations(Pageable pageable);
}
