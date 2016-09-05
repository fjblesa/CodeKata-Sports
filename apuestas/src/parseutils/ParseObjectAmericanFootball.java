package parseutils;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.json.JsonObject;
import utils.StringOfNumberUtils;

public class ParseObjectAmericanFootball implements ParseObject {

	protected static ParseObjectAmericanFootball uniqueInstance;
	protected static ObjectMapper mapper;
	private JsonObject jsonObject = new JsonObject();
	private String valueAmericanFootball;

	public static synchronized ParseObjectAmericanFootball getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new ParseObjectAmericanFootball();
			mapper = new ObjectMapper();
		}
		return uniqueInstance;
	}

	@Override
	public JsonObject convertToJson(String value) {

		String[] mapString = value.split("-");
		return transformValue(mapString);
	}

	private JsonObject transformValue(String[] stringValue) {
		int cont = 0;
		for (String string : stringValue) {
			jsonObject = convertTeam(string, cont);
			cont++;
		}
		return jsonObject;
	}

	private JsonObject convertTeam(String value, int team) {
		valueAmericanFootball = value;
		String teamName = "teamAName";
		String teamScore = "teamAScore";
		String period = "";
		String name = "";
		String score = "";

		if (team == 1) {
			teamName = "teamBName";
			teamScore = "teamBScore";
			period = stractPeriod(value.substring(1, value.length()));
		}
		String[] values = valueAmericanFootball.split(" ");

		for (String string : values) {
			if (!StringOfNumberUtils.isNumeric(string)) {
				name = name + string + " ";
			} else {
				score = string;
			}
		}
		jsonObject.put(teamName, name.substring(0, name.length() - 1));
		jsonObject.put(teamScore, score);
		if (period != "")
		{
			jsonObject.put("currentPeriod", period);
		}
		return jsonObject;
	}

	private String stractPeriod(String value) {
		int cont = StringOfNumberUtils.containsNumericPosition(value);
		if (cont != 0) {
			valueAmericanFootball = valueAmericanFootball.substring(0, cont);
			return value.substring(cont, value.length());
		}
		return "";
	}
}
