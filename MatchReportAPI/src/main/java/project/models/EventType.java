package project.models;

public enum EventType {
	GOAL(0), YELLOW_CARD(1), RED_CARD(2), BLUE_CARD(3), ASSIST(4),
	OWN_GOAL(5), OFFSIDE(6), PENALITY(7), MISSED_PENALITY(8), SUBSTITUTION(9),
	START_PERIOD(10), END_PERIOD(11), MATCH_SUSPENDED(12), MATCH_RESUMED(13);	
	
	private int value;

	private EventType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
