package back;

public class Session {
	
	private String game;
	private float stake;
	private String loc;
	private float buy;
	private float cash;
	private float hours;
	
	public Session(String game, String stake, String loc, float buy, float cash, float hours) {
		this.game = game;
		//regex stake for float value
		this.loc = loc;
		this.buy = buy;
		this.cash = cash;
		this.hours = hours;
	}
}
