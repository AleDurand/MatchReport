package project.exceptions;

public class EntityAlreadyExistsException extends CustomException {

	private static final long serialVersionUID = 1L;

	public EntityAlreadyExistsException(String code, Object[] args) {
		super(code, args);
	}

}
