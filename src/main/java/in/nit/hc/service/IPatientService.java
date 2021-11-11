package in.nit.hc.service;

import java.util.List;

import in.nit.hc.entity.Patient;

public interface IPatientService {

	public Long savePatient(Patient patient);
	
	public Patient getOnePatient(Long patientId);
	
	public List<Patient> getAllPatient();
	
	public void updatePatient(Patient patient);
	
	public void deletePatient(Long id);
}
