package in.nit.hc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nit.hc.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
	
	@Query("SELECT id,firstName,lastName FROM Doctor")
	public List<Object []> getIdAndName();
	
	@Query("SELECT d FROM Doctor d INNER JOIN d.specialization as s WHERE s.id=:specId")
	public List<Doctor> getDoctorBySpecName(Long specId);

}
