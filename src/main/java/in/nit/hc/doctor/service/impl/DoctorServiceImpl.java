package in.nit.hc.doctor.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.hc.doctor.entity.Doctor;
import in.nit.hc.doctor.exception.DoctorNotFoundException;
import in.nit.hc.doctor.repository.DoctorRepository;
import in.nit.hc.doctor.service.IDoctorService;
import in.nit.hc.util.CollectionUtil;

@Service
public class DoctorServiceImpl implements IDoctorService{
	
	@Autowired
	private DoctorRepository doctorRepo;
	
	@Override
	public Long saveDoctor(Doctor doctor) {
		
		doctor = doctorRepo.save(doctor);
		
		return doctor.getId();
	}

	@Override
	public List<Doctor> getAllDoctors() {
		
		List<Doctor> docList = doctorRepo.findAll();
		
		return docList;
	}

	@Override
	public void updateDoctor(Doctor doctor) {
		
		doctorRepo.save(doctor); 
	}

	@Override
	public void removeDoctor(Long id) {
		
		doctorRepo.delete(getOneDoctor(id));
	}

	@Override
	public Doctor getOneDoctor(Long id) {
		Doctor doctor = null;
		Optional<Doctor> opt = doctorRepo.findById(id);
		
		if(opt.isPresent()) {
			doctor =  opt.get();
		}else {
			opt.orElseThrow(
					() -> new DoctorNotFoundException("Doctor ("+id+") not available!!")
					);
		}
		return doctor;
	}

	@Override
	public Map<Long, String> getIdAndName() {
		List<Object[]> list = doctorRepo.getIdAndName(); 
		Map<Long, String> map = CollectionUtil.convertToMapIndex(list);
		
		return map;
	}

}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

