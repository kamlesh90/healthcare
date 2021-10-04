package in.nit.hc.doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.hc.doctor.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

}
