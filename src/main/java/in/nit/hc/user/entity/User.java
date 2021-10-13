package in.nit.hc.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_tab")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usr_id_col")
	private Long id;
	
	@Column(name = "usr_display_name_col")
	private String displayName;
	
	@Column(name = "usr_uname_col")
	private String userName;
	
	@Column(name = "usr_pwd_col")
	private String password;
	
	@Column(name = "usr_role_col")
	private String role;
}
