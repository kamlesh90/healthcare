package in.nit.hc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import in.nit.hc.constants.SlotStatus;
import lombok.Data;

@Data
@Entity
@Table(
		name = "slot_req_tab",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = {"pat_id_fk_col","appt_id_fk_col"})
		}
	  )
public class SlotRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "slot_id_col")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "pat_id_fk_col")
	private Patient patient;
	
	@OneToOne
	@JoinColumn(name = "appt_id_fk_col")
	private Appointment appointment;
	
	@Column(name = "slot_status_col")
	private String status;
}


























