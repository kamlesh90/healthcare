package in.nit.hc.service;

import java.util.List;
import java.util.Map;

import in.nit.hc.entity.Doctor;

public interface IDoctorService {

	public Long saveDoctor(Doctor doc);  	// 1. save
	
	public List<Doctor> getAllDoctors(); 	// 2. all
	
	public void updateDoctor(Doctor doc);	// 3. update
	
	public void removeDoctor(Long id);		//4. delete
	
	public Doctor getOneDoctor(Long id);	//5. getOne	
	
	public Map<Long, String> getIdAndName(); 
	
	List<Doctor> getDoctorBySpecName(Long specId);
	
	long getDocCount();
}
