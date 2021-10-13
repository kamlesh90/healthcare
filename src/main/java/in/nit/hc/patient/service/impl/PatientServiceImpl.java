package in.nit.hc.patient.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.hc.patient.entity.Patient;
import in.nit.hc.patient.exception.PatientNotFoundException;
import in.nit.hc.patient.repository.PatientRepository;
import in.nit.hc.patient.service.IPatientService;

@Service
public class PatientServiceImpl implements IPatientService{

	@Autowired
	private PatientRepository patientRepository; 
	
	@Override
	public Long savePatient(Patient patient) {
		
		patient = patientRepository.save(patient);
		
		return patient.getId();
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


}
