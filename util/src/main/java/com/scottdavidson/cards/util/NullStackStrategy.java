package com.scottdavidson.cards.util;

import java.util.ArrayList;
import java.util.List;

/**
 * A strategy that can be used when there is no restrictions (strategy).
 * 
 * @author scdavidson
 * 
 */
public class NullStackStrategy implements StackStrategy {

	public static NullStackStrategy newNullStackStrategy() {
		return new NullStackStrategy();
	}
	
	/**
	 * Always returns true :-)
	 */
	@Override
	public boolean cardCanBePushed(Card topCard, Card candidateCard) {
		return true;
	}
	
	/**
	 * Can't tell any specifics so must return an empty list.
	 */
	@Override
	public List<Card> cardsThatCanBePlayed(Card topCard) {
		return new ArrayList<Card>();
	}

	/**
	 * Always return false :-) 
	 */
	@Override
	public boolean isComplete(Card topCard) {
		return false;
	}

	private NullStackStrategy() {
		
	}

}
