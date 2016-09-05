package parseutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.json.JsonObject;
import utils.StringOfNumberUtils;

public class ParseObjectTennis implements ParseObject {

	protected static ParseObjectTennis uniqueInstance;
	protected static ObjectMapper mapper;
	private JsonObject jsonObject = new JsonObject();
	private static Map<String, String> scoreBoard;
	private static Map<String, String> elements;

	public static synchronized ParseObjectTennis getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new ParseObjectTennis();
			mapper = new ObjectMapper();
			scoreBoard = new HashMap<>();
			elements = new HashMap<>();
			elements.put("title", "sets");
		}
		return uniqueInstance;
	}

	@Override
	public JsonObject convertToJson(String value) {

		String[] teams = value.split("-");
		transformValue(teams);
		return jsonObject;
	}

	private void transformValue(String[] teams) {
		int count = 0;
		for (String team : teams) {
			convertTeam(team, count);
			count++;
		}

		scoreBoard.putAll(elements);
		jsonObject.put("scoreBoard", scoreBoard);
	}

	private void convertTeam(String value, int team) {

		if (team == 0) {
			convertTeamA(value);
		} else if (team == 1) {
			convertTeamB(value);
		}

	}

	private void convertTeamB(String value) {
		String teamBName = "teamBName";
		String teamBScore = "teamBScore";
		String teamBGames = "teamBGames";
		String teamBserving = "teamBServing";

		String[] values = value.split(" ");
		List<String> listNumbers = new ArrayList<>();
		List<String> listNames = new ArrayList<>();

		for (String param : values) {

			if (param.equals("Adv") || StringOfNumberUtils.containsNumeric(param)
					|| StringOfNumberUtils.isNumeric(param)) {
				listNumbers.add(param);
			} else if (!param.equals("Adv")) {

				listNames.add(param);
			}
		}

		String name = listNames.stream().collect(Collectors.joining(" "));
		boolean serving = name.contains("*");
		name = serving ? name.replace("*", "") : name;

		jsonObject.put(teamBName, name);

		elements.put(teamBScore, listNumbers.get(2).replaceAll("[()]", ""));
		jsonObject.put(teamBGames, listNumbers.get(1));
		String score = listNumbers.contains("Adv") ? "Adv" : listNumbers.get(0);
		jsonObject.put(teamBScore, score);
		jsonObject.put(teamBserving, serving);
	}

	private void convertTeamA(String value) {
		String teamAName = "teamAName";
		String teamAScore = "teamAScore";
		String teamAGames = "teamAGames";

		String[] values = value.split(" ");
		List<String> listNumbers = new ArrayList<>();
		List<String> listNames = new ArrayList<>();

		for (String param : values) {
			if (param.equals("Adv") || StringOfNumberUtils.containsNumeric(param)
					|| StringOfNumberUtils.isNumeric(param)) {
				listNumbers.add(param);
			} else if (!param.equals("Adv")) {
				listNames.add(param);
			}
		}

		String name = listNames.stream().collect(Collectors.joining(" "));
		jsonObject.put(teamAName, name);
		elements.put(teamAScore, listNumbers.get(0).replaceAll("[()]", ""));
		jsonObject.put(teamAGames, listNumbers.get(1));
		String score = listNumbers.contains("Adv") ? "Adv" : listNumbers.get(2);
		jsonObject.put(teamAScore, score);
	}
}
