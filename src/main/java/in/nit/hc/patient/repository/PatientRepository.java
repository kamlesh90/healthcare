package in.nit.hc.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.hc.patient.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{

}
