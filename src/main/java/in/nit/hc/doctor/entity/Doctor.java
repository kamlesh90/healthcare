package in.nit.hc.doctor.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import in.nit.hc.specialization.entity.Specialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "doctor_tab")
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
 
	@Id
	@Column(name = "doc_id_col")
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;
	
	@Column(name = "doc_fn_col")
	private String firstName;
	
	@Column(name = "doc_ln_col")
	private String lastName;
	
	@Column(name = "doc_email_col")
	private String email;
	
	@Column(name = "doc_address_col")
	private String address;
	
	@Column(name = "doc_mobile_col")
	private String mobile;
	
	@Column(name = "doc_gen_col")
	private String gender;
	
	@Column(name = "doc_note_col")
	private String note;
	
	@Column(name = "doc_photo_col")
	private String photoLoc;
	
	@ManyToOne
	@JoinColumn(name = "spec_id_fk_col")
	private Specialization specialization;
	

/**	
	@Transient
	private String photoPath;
	
	public String getPhotoPath() {                          // for local file upload
		
		if(photoLoc == null || id == null) return null;
		else return "/user-photos/" + id + "/" +photoLoc;
	}
*/
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	