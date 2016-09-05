package enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SportType {
	FOOTBALL(0), TENNIS(1), AMERICANFOOTBALL(2), UNDEFINED(3);

	private int id;

	SportType(int id) {
		this.id = id;
	}

	@JsonValue
	public int getId() {
		return id;
	}
}
