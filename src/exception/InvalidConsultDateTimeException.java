package exception;

public class InvalidConsultDateTimeException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidConsultDateTimeException(String message) {
		super(message);
	}
}
