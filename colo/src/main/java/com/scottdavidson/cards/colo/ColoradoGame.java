package com.scottdavidson.cards.colo;

import com.scottdavidson.cards.util.Card;
import com.scottdavidson.cards.util.Deck;
import com.scottdavidson.cards.util.Foundation;
import com.scottdavidson.cards.util.Game;
import com.scottdavidson.cards.util.Tableaus;

/**
 * Represents the entire game of Colorado, holding all of the elements.
 * 
 * @author scdavidson
 *
 */
public class ColoradoGame implements Game {

	private Foundation ascendingFoundation;
	private Foundation descendingFoundation;
	private Tableaus tableaus;
	private Deck deck;
	private Card deckCard = null;
	
	
	public Foundation getAscendingFoundation() {
		return ascendingFoundation;
	}


	public void setAscendingFoundation(Foundation ascendingFoundation) {
		this.ascendingFoundation = ascendingFoundation;
	}


	public Foundation getDescendingFoundation() {
		return descendingFoundation;
	}


	public void setDescendingFoundation(Foundation descendingFoundation) {
		this.descendingFoundation = descendingFoundation;
	}


	public Tableaus getTableaus() {
		return tableaus;
	}


	public void setTableaus(Tableaus tableaus) {
		this.tableaus = tableaus;
	}

	/**
	 * The deck card is the card that is in play which is pulled from the 
	 * deck. This method returns the card (but doesn't actually "play" it, 
	 * that's a separate method) for evaluation in a play. <br>
	 * <p>
	 * If the deck card is null, then deal a new card from the Deck.
	 * 
	 * @return the deck card (or Null if none exists)
	 */
	Card getDeckCard() {
		
		// If null, attempt to deal a new card
		if ( null == this.deckCard ) {
			this.deckCard = this.deck.dealCard();
		}
		
		return this.deckCard;
		
	}

	public Deck getDeck() {
		return deck;
	}


	public void setDeck(Deck deck) {
		this.deck = deck;
	}


	private ColoradoGame () {
		this.deck = Deck.newShuffledDoubleDeck();
	}
}
