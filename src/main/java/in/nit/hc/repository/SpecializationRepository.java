package in.nit.hc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nit.hc.entity.Specialization;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

	@Query("SELECT COUNT(specCode) FROM Specialization WHERE specCode=:specCode")
	public Integer getSpecCodeCount(String specCode);
	
	@Query("SELECT COUNT(specCode) FROM Specialization WHERE specCode=:specCode AND id!=:id")
	public Integer getSpecCodeCount(String specCode, Long id);
	
	@Query("SELECT id, specName FROM Specialization")  // here when we select more then one coloum in springdatajpa then springDataJpa return "List<Object []>"
	public List<Object []> getSpecIdAndName();           
}
