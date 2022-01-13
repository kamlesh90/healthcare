package in.nit.hc.service.impl;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import in.nit.hc.constants.UserRoles;
import in.nit.hc.entity.Doctor;
import in.nit.hc.entity.User;
import in.nit.hc.exception.DoctorNotFoundException;
import in.nit.hc.repository.DoctorRepository;
import in.nit.hc.service.IDoctorService;
import in.nit.hc.service.IUserService;
import in.nit.hc.util.CollectionUtil;
import in.nit.hc.util.EmailUtil;
import in.nit.hc.util.PwdGeneratorUtil;

import java.util.List;

@Service
public class DoctorServiceImpl implements IDoctorService{
	
	@Autowired
	private DoctorRepository doctorRepo;
	
	@Autowired
	private EmailUtil emailUtil;
	
	@Autowired
	private IUserService userService;
	
	@Override
	public Long saveDoctor(Doctor doctor) {
		
		Long docId = doctorRepo.save(doctor).getId();
		
		if(docId!=null) {
			
			String pwd = PwdGeneratorUtil.genratePassword();
			
			User user = new User();
			user.setDisplayName(doctor.getFirstName()+""+doctor.getLastName()); 
			user.setUserName(doctor.getEmail());
			user.setPassword(pwd);
			user.setRole(UserRoles.DOCTOR.name()); 
			
			Long userId = userService.saveUser(user);
			
			if(userId!=null) {
				new Thread(
						new Runnable() {
							
							@Override
							public void run() {
								 String text = "your Username : "+doctor.getEmail()+
								 	   	   " password : "+pwd;
								emailUtil.send(
										doctor.getEmail(), 
										"DOCTOR ADDED SUCCESS",
										text,
										new ClassPathResource("/static/myresp/Welcome.pdf")); 
							}
						}
						  ).start();
			}
		}
		
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

	@Override
	public List<Doctor> getDoctorBySpecName(Long specId) {
		List<Doctor> docList=null;
		
		if(specId<=0) {
			docList = doctorRepo.findAll();
		}else {
			docList = doctorRepo.getDoctorBySpecName(specId);
		}
		
		return docList;
	}

	public long getDocCount() {

		return doctorRepo.count();
	}

}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

