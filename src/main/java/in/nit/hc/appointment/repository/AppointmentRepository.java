package in.nit.hc.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.hc.appointment.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

}
