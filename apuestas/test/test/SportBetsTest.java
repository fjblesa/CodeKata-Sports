package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import io.vertx.core.json.JsonObject;
import sportbets.Result;

public class SportBetsTest {

	@Test
	public void whenCallWithFootballThenReturnJsonObject() {
		String input = "F.C. Barcelona 3-2 Real Madrid";
		Result resultadoFactory = new Result(input);
		JsonObject resultadoFutbol = resultadoFactory.execute();

		assertFalse(resultadoFutbol.isEmpty());

		assertEquals("F.C. Barcelona", resultadoFutbol.getString("teamAName"));
		assertEquals("3", resultadoFutbol.getString("teamAScore"));
		assertEquals("Real Madrid", resultadoFutbol.getString("teamBName"));
		assertEquals("2", resultadoFutbol.getString("teamBScore"));
	}
	
	@Test
	public void whenCallWithFootballThenReturnJsonObjectOther() {
		String input = "Albacete Balompie C.F. 8-1 Atletico de Madrid";
		Result resultadoFactory = new Result(input);
		JsonObject resultadoFutbol = resultadoFactory.execute();

		assertFalse(resultadoFutbol.isEmpty());

		assertEquals("Albacete Balompie C.F.", resultadoFutbol.getString("teamAName"));
		assertEquals("8", resultadoFutbol.getString("teamAScore"));
		assertEquals("Atletico de Madrid", resultadoFutbol.getString("teamBName"));
		assertEquals("1", resultadoFutbol.getString("teamBScore"));
	}

	@Test
	public void whenCallWithTennisThenReturnJsonObject() {
		String input = "Anna Karolina Schmiedlova (1) 1 40-Adv 1 (0) *Varvara Lepchenko";
		Result resultadoFactory = new Result(input);
		JsonObject resultTenis = resultadoFactory.execute();

		assertFalse(resultTenis.isEmpty());

		assertEquals("Anna Karolina Schmiedlova", resultTenis.getString("teamAName"));
		assertEquals("1", resultTenis.getString("teamAGames"));
		assertEquals("40", resultTenis.getString("teamAScore"));
		assertEquals("Varvara Lepchenko", resultTenis.getString("teamBName"));
		assertEquals("1", resultTenis.getString("teamBGames"));
		assertEquals("Adv", resultTenis.getString("teamBScore"));
		assertEquals(true, resultTenis.getBoolean("teamBServing"));
		JsonObject scoreBoard = resultTenis.getJsonObject("scoreBoard");
		assertEquals("1", scoreBoard.getString("teamAScore"));
		assertEquals("0", scoreBoard.getString("teamBScore"));
	}

	@Test
	public void whenCallWithAmericanFootballThenReturnJsonObject() {
		String input = "Pittsburgh Steelers 3-7 Minnesota Vikings 3rd Quarter";
		Result resultadoFactory = new Result(input);
		JsonObject resultadoAmericanFootball = resultadoFactory.execute();

		assertFalse(resultadoAmericanFootball.isEmpty());

		assertEquals("Pittsburgh Steelers", resultadoAmericanFootball.getString("teamAName"));
		assertEquals("3", resultadoAmericanFootball.getString("teamAScore"));
		assertEquals("Minnesota Vikings", resultadoAmericanFootball.getString("teamBName"));
		assertEquals("7", resultadoAmericanFootball.getString("teamBScore"));
		assertEquals("3rd Quarter", resultadoAmericanFootball.getString("currentPeriod"));
	}
}
