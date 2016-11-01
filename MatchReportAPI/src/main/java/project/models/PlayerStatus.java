package project.models;

public enum PlayerStatus {
	ENABLED(0), SUSPENDED(1), DISABLED(2), UNSUSCRIBED(3);

	private int value;

	private PlayerStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
