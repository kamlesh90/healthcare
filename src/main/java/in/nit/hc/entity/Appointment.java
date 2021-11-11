package in.nit.hc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "appointment_tab")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appiont_id_col")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "doc_appoint_id_fk_col")
	private Doctor doctor;
	
	@DateTimeFormat(iso = ISO.DATE)  // @DataTimeFormate -> convert the String format date which we enter in UI that is Sting format to util.Date format > 
										 // < iso is an enum used for save date only there is more option like DateTiem etc... 
	@Temporal(TemporalType.DATE)     // @Temporal -> again it is used for util.Date to database format > TemporalType.Date < again for date  
	@Column(name = "appoint_dt_col")
	private Date appointmentDt;
	
	@Column(name = "appoint_slots_col")
	private Integer noOfSlots;
	
	@Column(name = "appoint_dtls_col", length = 100, nullable = false) 
	private String details;
	
	@Column(name = "appoint_fees_col")
	private Double fees;
	
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

