package project.models;

public enum MatchStatus {
	PENDING(0), IN_PROGRESS(1), FINALIZED(2), SUSPENDED(3), DELAYED(4);

	private int value;

	private MatchStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}