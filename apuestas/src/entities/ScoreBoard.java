package entities;

import java.util.HashMap;
import java.util.Map;

public class ScoreBoard {
	private Map<String, String> elements;

	public ScoreBoard() {
		this.elements = new HashMap<>();
	}

	public Map<String, String> getElements() {
		return elements;
	}

	public void setElements(Map<String, String> elements) {
		this.elements = elements;
	}
}
