package sportbets;

public class AppMain {
	public static void main(String[] args) {

		System.out.println("\n ---- Sports bets ----");

		String inputFootball = "F.C. Barcelona 3-2 Real Madrid";
		System.out.println("\n FOOTBALL INPUT : " + inputFootball);

		Result resultFootball = new Result(inputFootball);
		System.out.println(resultFootball.execute());
		
		String inputTennis = "Anna Karolina Schmiedlova (1) 1 40-Adv 1 (0) *Varvara Lepchenko";
		System.out.println("\n TENNIS INPUT : " + inputTennis);
		
		Result resultTennis= new Result(inputTennis);
		System.out.println(resultTennis.execute());
		
		String inputAmericanFootball = "Pittsburgh Steelers 3-7 Minnesota Vikings 3rd Quarter";
		System.out.println("\n AMERICAN FOOTBALL INPUT : "+inputAmericanFootball);
		
		Result resultAmericanFootball= new Result(inputAmericanFootball);
		System.out.println(resultAmericanFootball.execute());
	}
}
