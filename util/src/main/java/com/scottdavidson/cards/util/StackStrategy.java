package com.scottdavidson.cards.util;

public interface StackStrategy {
	
	public boolean cardCanBePushed(Card topCard, Card candidateCard);

}
