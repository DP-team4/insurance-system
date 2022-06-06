package exception;

public class InvalidAccidentDateTimeException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidAccidentDateTimeException(String message) {
		super(message);
	}
}
