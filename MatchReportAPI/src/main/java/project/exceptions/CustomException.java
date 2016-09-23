package project.exceptions;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String code;
	private Object[] args;

	public CustomException(String code, Object[] args) {
		super();
		this.code = code;
		this.args = args;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

}