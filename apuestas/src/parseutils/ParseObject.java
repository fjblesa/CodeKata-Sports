package parseutils;

import io.vertx.core.json.JsonObject;

public interface ParseObject {
	public JsonObject convertToJson(String value);
}
