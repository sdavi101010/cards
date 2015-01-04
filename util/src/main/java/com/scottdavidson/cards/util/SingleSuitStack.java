package com.scottdavidson.cards.util;

/**
 * Represents a stack of Cards of a particular suit. The stack can be configured
 * to be either ASC or DESC.
 * 
 * @author scdavidson
 * 
 */
public class SingleSuitStack {

	private final Card.Suit suit;

	public Card.Suit getSuit() {
		return suit;
	}

	private SingleSuitStack(Card.Suit suit) {
		this.suit = suit;
	}

}
