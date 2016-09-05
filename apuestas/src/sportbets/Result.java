package sportbets;

import java.util.stream.Stream;

import enums.SportType;
import io.vertx.core.json.JsonObject;
import parseutils.ParseObject;
import parseutils.ParseObjectAmericanFootball;
import parseutils.ParseObjectFootball;
import parseutils.ParseObjectTennis;
import utils.StringOfNumberUtils;

public class Result {

	private String input;
	private ParseObject parser;

	public Result(String input) {
		this.input = input;
	}

	public JsonObject execute() {
		JsonObject result = null;
		SportType sport = howSportIsThis(this.input);

		switch (sport) {
		case FOOTBALL:
			parser = ParseObjectFootball.getInstance();
			result = parser.convertToJson(this.input);
			break;

		case TENNIS:
			parser = ParseObjectTennis.getInstance();
			result = parser.convertToJson(this.input);
			break;

		case AMERICANFOOTBALL:
			parser = ParseObjectAmericanFootball.getInstance();
			result = parser.convertToJson(this.input);
			break;
		default:
			throw new RuntimeException("Input sport not exist");
		}
		return result;
	}

	private SportType howSportIsThis(String input) {

		String[] firtsValues = input.split("-");
		String[] values = firtsValues[1].split(" ");

		if (isAmericanFootball(values)) {
			return SportType.AMERICANFOOTBALL;
		} else if (isFootball(values)) {
			return SportType.FOOTBALL;
		} else if (isTennis(values)) {
			return SportType.TENNIS;
		}
		return SportType.UNDEFINED;
	}

	private boolean isFootball(String[] values) {
		int countNumbers = StringOfNumberUtils.countNumbers(values);
		return countNumbers == 1 ? true : false;
	}

	private boolean isTennis(String[] values) {
		int countNumbers = StringOfNumberUtils.countNumbers(values);
		return countNumbers >= 2 ? true : false;
	}

	private boolean isAmericanFootball(String[] values) {
		boolean isAmericanFootball = Stream.of(values).filter(v -> v.contains("Quarter")).findAny().isPresent();
		return isAmericanFootball;
	}
}
