package project.exceptions;

public class EntityNotFoundException extends CustomException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String code, Object[] args) {
		super(code, args);
	}
}
