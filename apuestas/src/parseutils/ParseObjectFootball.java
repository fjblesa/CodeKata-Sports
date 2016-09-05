package parseutils;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.json.JsonObject;
import utils.StringOfNumberUtils;

public class ParseObjectFootball implements ParseObject {

	protected static ParseObjectFootball uniqueInstance;
	protected static ObjectMapper mapper;
	private JsonObject jsonObject = new JsonObject();

	public static synchronized ParseObjectFootball getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new ParseObjectFootball();
			mapper = new ObjectMapper();
		}
		return uniqueInstance;
	}


	@Override
	public JsonObject convertToJson( String value) {

		String[] mapString = value.split("-");
		return transformValue(mapString);
	}

	private JsonObject transformValue(String[] stringValue) {
		int cont = 0;
		for (String string : stringValue) {			
			jsonObject = convertTeam(string, cont);
			cont ++;
		}
		return jsonObject;
	}

	private JsonObject convertTeam(String value,int team) {
		String teamName = "teamAName";
		String teamScore = "teamAScore";
		if(team == 1)
		{
			teamName = "teamBName";
			teamScore = "teamBScore";
		}
		
		String name = "";
		String score = "";
		String[] values = value.split(" ");
		for (String string : values) {
			if(!StringOfNumberUtils.isNumeric(string))
			{
				name = name + string +" ";
			}
			else{
				score = string;
			}
		}
		jsonObject.put(teamName,name.substring(0,name.length()-1));
		jsonObject.put(teamScore, score);
		return jsonObject;
	}
}
