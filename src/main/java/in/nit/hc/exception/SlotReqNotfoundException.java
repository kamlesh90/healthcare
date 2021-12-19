package in.nit.hc.exception;

public class SlotReqNotfoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public SlotReqNotfoundException() {
		super();
	}
	
	public SlotReqNotfoundException(String message) {
		super(message);
	}

}
