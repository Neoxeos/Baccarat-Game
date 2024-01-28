import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BaccaratDealer {
	ArrayList<Card> deck;
	
	/*
	 * Generate a deck of 52 standard cards
	 * input: None
	 * output: None
	 * mutates our ArrayList<Card> deck to have 52 cards
	 */
	public void generateDeck() {
		this.deck = new ArrayList<>();
		int count = 0;
		Card toAdd;

		for (int i = 1; i <= 52; i++) { // generate 52 cards
			count += 1;
			if (count >= 1 & count < 14) {
				toAdd = new Card("diamond", i);
				deck.add(toAdd);
			} else if (count >= 14 & count < 27) {
				toAdd = new Card("spades", i - 13);
				deck.add(toAdd);
			} else if (count >= 27 & count < 30) {
				toAdd = new Card("hearts", i - 26);
				deck.add(toAdd);
			} else {
				toAdd = new Card("clubs", i - 39);
				deck.add(toAdd);
			}
		}
	};
	
	/*
	 * deal two cards from our deck and return them in an ArrayList<Card>
	 * input: None
	 * output: ArrayList<Card> a list of cards
	 */
	public ArrayList<Card> dealHand(){
		ArrayList<Card> result = new ArrayList<Card>(2);
		
		// get the top of our deck position
		int topOfDeck = deck.size() - 1;
		
		// add the two cards from the top of the deck to result since we are drawing from our deck
		result.add(deck.get(topOfDeck));
		this.deck.remove(topOfDeck);
		topOfDeck--; // decrement since we just took a card
		result.add(deck.get(topOfDeck));
		this.deck.remove(topOfDeck);
		
		return result;
	}
	
	/*
	 * deal a single card and return it
	 * input: None
	 * output: Card. a single card drawn from our deck
	 */
	public Card drawOne() {
		Card result = deck.get(deck.size() - 1); // get one card from top of deck
		
		return result;
	}
	
	/*
	 * create a new deck of 52 cards and “shuffle”
	 * input: None
	 * output: None 
	 */
	public void shuffleDeck() {
		this.generateDeck();
		Collections.shuffle(deck);
	}
	
	/*
	 * returns the amount of cards in our deck
	 * input: None
	 * output: int. Number of cards
	 */
	public int deckSize() {
		return deck.size();
	}
}
