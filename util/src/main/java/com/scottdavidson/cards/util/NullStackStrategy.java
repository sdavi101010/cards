package com.scottdavidson.cards.util;

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
	
	private NullStackStrategy() {
		
	}

}
