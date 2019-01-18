package utils;

public class TrelloException extends Exception {

	private static final long serialVersionUID = 1L;
	private String code;
	private String responseContent;
	
	public TrelloException() {
		super();
	}

	public TrelloException(String message) {
		super(message);
	}

	public TrelloException(String code, String message, String responseContent) {
		super(message);
		this.code = code;
		this.responseContent = responseContent;
	}

	public TrelloException(String code, String message, String responseContent, Throwable cause) {
		super(message, cause);
		this.code = code;
		this.responseContent = responseContent;
	}

	public String getCode() {
		return code;
	}

	public String getResponseContent() {
		return responseContent;
	}

}
