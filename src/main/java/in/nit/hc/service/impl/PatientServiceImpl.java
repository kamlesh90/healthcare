package in.nit.hc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.nit.hc.constants.UserRoles;
import in.nit.hc.entity.Patient;
import in.nit.hc.entity.User;
import in.nit.hc.exception.PatientNotFoundException;
import in.nit.hc.repository.PatientRepository;
import in.nit.hc.service.IPatientService;
import in.nit.hc.service.IUserService;
import in.nit.hc.util.EmailUtil;
import in.nit.hc.util.PwdGeneratorUtil;

@Service
public class PatientServiceImpl implements IPatientService{

	@Autowired
	private PatientRepository patientRepository; 
	
	@Autowired
	private IUserService userServiceImpl;
		
	@Autowired 
	private BCryptPasswordEncoder passwordEncoder;
	 
	
	@Autowired
	private EmailUtil mailUtil;
	
	@Override
	public Long savePatient(Patient patient) {
		
		Long patientId = patientRepository.save(patient).getId();
		
		if(patientId!=null) {
			String genPwd = PwdGeneratorUtil.genratePassword();
			User user = new User();
			user.setDisplayName(patient.getFirstName()+" "+patient.getLastName());
			user.setUserName(patient.getEmail()); 
			user.setPassword(genPwd);
			user.setRole(UserRoles.PATIENT.name());
			
			Long genUsrId = userServiceImpl.saveUser(user);
			
			if(genUsrId!=null) {
				
				new Thread(
						 new Runnable() {
							public void run() {
							String text = "Your username : "+patient.getEmail()+
									      " password : "+genPwd;
								mailUtil.send(patient.getEmail(), "PATIENT ADDED ", text);
							}
						}
				).start();
			}
		}
		
		return patientId;
	}

	@Override
	public Patient getOnePatient(Long patientId) {
/*
		Optional<Patient> opt= patientRepository.findById(patientId);
		Patient patient=null;
		
		if(opt.isPresent()) {
			patient=opt.get();
		}else {
			opt.orElseThrow(
					  () -> new PatientNotFoundException("Patient ("+patientId+") not available.....")
							);
			
		}
		
		return patient;
*/		
		
		return patientRepository.findById(patientId).orElseThrow(
				() -> new PatientNotFoundException("PATIENT ("+patientId+") NOT AVAILABLE !!")
				);
	}

	@Override
	public List<Patient> getAllPatient() {
		List<Patient> patientList = patientRepository.findAll();
		return patientList;
	}

	@Override
	public void updatePatient(Patient patient) {
		patientRepository.save(patient);
	}

	@Override
	public void deletePatient(Long id) {
		
		patientRepository.delete(getOnePatient(id)); 
	}

	@Override
	public Patient getPatientByEmail(String email) {
		
		return patientRepository.findByEmail(email).get();
	}

	@Override
	public long getPatientCount() {

		return patientRepository.count();
	}


}
