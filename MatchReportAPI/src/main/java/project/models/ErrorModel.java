package project.models;

import java.io.Serializable;

public class ErrorModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer status;
	private String message;

	public ErrorModel(Integer status, String message) {
		this.status = status;
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}