
public class Card {
	String suite;
	int value;
	
	Card() {
		
	}
	
	Card(String theSuite, int theValue){
		// TODO
		suite = theSuite;
		value = theValue;
	}
	
	// getters
	public int getValue() {
		if (value > 9 | value < 1) {
			return 0;
		}
		return this.value;
	}
	
	public String getSuite() {
		return this.suite;
	}
}
