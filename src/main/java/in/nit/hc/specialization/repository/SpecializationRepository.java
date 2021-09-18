package in.nit.hc.specialization.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.hc.specialization.entity.Specialization;

public interface SpecializationRepository extends JpaRepository<Long, Specialization> {

}
