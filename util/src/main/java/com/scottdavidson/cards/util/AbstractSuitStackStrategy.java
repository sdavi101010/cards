package com.scottdavidson.cards.util;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public abstract class AbstractSuitStackStrategy implements StackStrategy {

	private Card.Suit suit;

	/**
	 * Test for as many "standard" things as possible; assumption is concrete
	 * classes may add additional tests.
	 * 
	 */
	@Override
	public boolean cardCanBePushed(Card topCard, Card candidateCard) {

		// Error check - candidate card must be non-null
		if (null == candidateCard) {
			throw new CardUtilException("Candidate Card is null");
		}

		// Error check : top card's suit must match 
		if (null != topCard && this.suit != topCard.getSuit() ) {
			throw new CardUtilException("Top card's suit doesn't match strategy - logic error! Top Card Suit = "
					+ topCard.getSuit() + " , Strategy Suit: " + this.suit);
		}

		// Test 0 : If top card is a Joker, candidate can't be pushed
		if (null != topCard && topCard.isJoker()) {
			return false;
		}

		// Test 1 : If candidate card is a Joker, candidate can't be pushed
		if (candidateCard.isJoker()) {
			return false;
		}

		// Test 2 : candidate card is the appropriate (expected) suit
		if (this.suit != candidateCard.getSuit()) {
			return false;
		}
		
		// Test 3 : if top card is null, candidate card must be the first valid card value
		if ( null == topCard ) {
			if ( candidateCard.getValue() == requiredValueOfFirstCard()) {
				return true;
			}
			else {
				return false;
			}
		}
		
		// Force some quava stuff .... 
		BiMap<String, Integer> bimap = HashBiMap.create();
		
		// For this abstract case, everything's good !
		return true;
	}
	
	protected abstract int requiredValueOfFirstCard();
	
	protected AbstractSuitStackStrategy(Card.Suit suit) {
		this.suit = suit;
	}

}
