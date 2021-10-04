package in.nit.hc.specialization.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.hc.specialization.entity.Specialization;
import in.nit.hc.specialization.exception.SpecializationNotFoundException;
import in.nit.hc.specialization.repository.SpecializationRepository;
import in.nit.hc.specialization.service.ISpecializationService;
import in.nit.hc.util.CollectionUtil;

@Service
public class SpecializationServiceImpl implements ISpecializationService{

	@Autowired
	private SpecializationRepository specRepo;
	
	@Override
	public Long saveSpecialization(Specialization spec) {
		
		return specRepo.save(spec).getId();
	}

	@Override
	public List<Specialization> getAllSpecializations() {
		
		List<Specialization> specList = specRepo.findAll();
		
		return specList;
	}

	@Override
	public void removeSpecialization(Long id) {
		
		//specRepo.deleteById(id);
		specRepo.delete(getOneSpecialization(id));
	}

	@Override
	public void updateSpecialization(Specialization spec) {
		
		specRepo.save(spec);
		
	}

	@Override
	public Specialization getOneSpecialization(Long id) {

/*		
		Specialization spec = null;
		Optional<Specialization> opt = specRepo.findById(id);
		
		if(opt.isPresent()) {
			spec = opt.get();
		}else {
			opt.orElseThrow(
							() -> new SpecializationNotFoundException("for ("+id+") Specialization Not Found")
					);
		}
*/		
		return specRepo.findById(id).orElseThrow(
											
				  							() -> new SpecializationNotFoundException("for id = ("+id+") Specialization Not Found")
												);
		
		//return spec;
	}

	@Override
	public Boolean isSpecCodeExist(String specCode) {
		
		/*
		 * Boolean exist = false;
		 * 
		 * if(specRepo.getSpecCodeCount(specCode)>0) { exist = true; }
		 * 
		 * return exist;
		 */
		
		return specRepo.getSpecCodeCount(specCode)>0;
	}
	
	@Override
	public Boolean isSpecCodeExist(String specCode, Long id) {

		return specRepo.getSpecCodeCount(specCode, id)>0;
	}

	@Override
	public Map<Long,String> getSpecIdAndName() {  
		List<Object[]> dynamicSpecIdAndNameObjectArrList = specRepo.getSpecIdAndName();

		Map<Long,String> dynamicSpecIdAndNameObjectArrMap = CollectionUtil.convertToMap(dynamicSpecIdAndNameObjectArrList); 
		
		return dynamicSpecIdAndNameObjectArrMap;
	}


}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	