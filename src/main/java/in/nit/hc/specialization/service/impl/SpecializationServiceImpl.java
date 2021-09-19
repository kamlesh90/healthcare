package in.nit.hc.specialization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.hc.specialization.entity.Specialization;
import in.nit.hc.specialization.repository.SpecializationRepository;
import in.nit.hc.specialization.service.ISpecializationService;

@Service
public class SpecializationServiceImpl implements ISpecializationService{

	@Autowired
	private SpecializationRepository specRepo;
	
	@Override
	public Long saveSpecialization(Specialization spec) {
		
		return specRepo.save(spec).getId();
	}

	
}
