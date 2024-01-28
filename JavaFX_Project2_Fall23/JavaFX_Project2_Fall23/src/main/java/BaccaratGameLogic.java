import java.util.ArrayList;

public class BaccaratGameLogic {
	
	/*
	 * evaluate two hands at the end of the game and return a string depending on the winner: “Player”, “Banker”, “Draw”
	 * @params: hand1, hand2 => the two hands to compare. hand1 => player, hand2 => banker
	 * @output: string: "Player", Banker", "Draw" depending on outcome of game
	 */
	public String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2) {
		// we take hand1 to be the player's hand
		int winB;
		int winP;
		// check player hand
		int sum1 = handTotal(hand1);
		
		// check banker hand
		int sum2 = handTotal(hand2);
		
		if ((sum1 == sum2)) {
			return "Draw";
		} else {
			// check which closer to 9
			winP = (9 - sum1);
			winB = (9 - sum2);
			
			// winner closer to 9
			if (winP > winB) {
				return "Player";
			} else if (winB > winP) {
				return "Banker";
			} else {
				return "Draw";
			}
		}
		
	}
	
	/*
	 * take a hand and return how many points that hand is worth.
	 * @params: hand => arrayList to be evaluated
	 * @output: int => total in hand according to Baccarat rules
	 */
	public int handTotal(ArrayList<Card> hand) {
		int sum = 0;
		
		for (Card c : hand) {
			sum += c.getValue();
		}
		
		// Applying Baccarat Rules
		if (sum > 9) {
			sum = sum % 10;
		}
		
		return sum;
	}
	
	/*
	 * return true if either one should be dealt a third card, otherwise return false.
	 * @params: hand, playerCard => to check if banker has been dealt a third card
	 * @returns: boolean => depending on if a third card is dealt
	 */
	public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard) {
		if (playerCard == null) {
			return true;
		}
		
		if (handTotal(hand) > 6) {
			return false;
		} else if (handTotal(hand) == 0 | handTotal(hand) == 1 | handTotal(hand) == 2) {
			return true;
		} else if (handTotal(hand) == 3 ){
			if (playerCard.getValue() != 8) { 
				return false;
			} else {
				return true;
			}
		} else if (handTotal(hand) == 4) {
			if (playerCard.getValue() > 1 & playerCard.getValue() < 8) {
				return true;
			}
		} else if (handTotal(hand) == 5) {
			if (playerCard.getValue() > 3 & playerCard.getValue() < 8) {
				return true;
			}
		} else {
			if (playerCard.getValue() > 5 & playerCard.getValue() < 8) {
				return true;
			}
		}
		return true;
	}
	
	/*
	 * return true if either one should be dealt a third card, otherwise return false.
	 * @params: hand, playerCard => to check if banker has been dealt a third card
	 * @returns: boolean => depending on if a third card is dealt
	 */
	public boolean evaluatePlayerDraw(ArrayList<Card> hand) {
		if (handTotal(hand) > 5) {
			return false;
		} else {
			return true;
		}
	}
	
}
