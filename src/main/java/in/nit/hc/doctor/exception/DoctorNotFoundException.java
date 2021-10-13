package in.nit.hc.doctor.exception;

public class DoctorNotFoundException extends RuntimeException{
	
	/**
	 * @author Kamlesh Makode
	 */
	private static final long serialVersionUID = 1L;
	
	public DoctorNotFoundException() {
		super();
	}
	public DoctorNotFoundException(String msg) {
		super(msg);
	}
	
}
