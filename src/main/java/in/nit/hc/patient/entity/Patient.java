package in.nit.hc.patient.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "patient_tab")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "pat_first_name_col", length = 25, nullable = false, unique = false)
	private String firstName;
	
	@Column(name = "pat_last_name_col", length = 25, nullable = false, unique = false)
	private String lastName;
	
	@Column(name = "pat_gen_col", length = 10, nullable = false, unique = false)
	private String gender;
	
	@Column(name = "pat_phone_num_col", length = 15, nullable = false, unique = true)
	private String phoneNum;
	
	@Column(name = "pat_dob_col", length =  225, nullable = false, unique = false)
	@DateTimeFormat(iso = ISO.DATE)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@Column(name = "pat_marital_status_col")
	private String maritalStatus;
	
	@Column(name = "pat_present_address_col")
	private String presentAddress;
	
	@Column(name = "pat_com_address_col")
	private String communicationAddress;
	
	@ElementCollection
	@CollectionTable(name = "pat_med_history_tab", joinColumns = @JoinColumn(name="pat_med_history_fk_col"))
	@Column(name = "pat_med_history_col")
	private Set<String> pastMedHistory;

	@Column(name = "pat_other_med_hist_col")
	private String ifOtherMedHistory;
	
	@Column(name = "pat_other_dtls_col")
	private String otherDtls;
	
	
}
