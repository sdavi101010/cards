package com.scottdavidson.cards.util;

public class AscendingSuitStackStrategy extends AbstractSuitStackStrategy {

	public static AscendingSuitStackStrategy newAscendingSuitStackStrategy(
			Card.Suit suit) {
		return new AscendingSuitStackStrategy(suit);
	}

	@Override
	public boolean cardCanBePushed(Card topCard, Card candidateCard) {

		// Let the abstract parent do its check first ...
		if ( !super.cardCanBePushed(topCard, candidateCard)) {
			return false;
		}
		
		// No other tests (yet)
		return true;
	}
	
	protected int requiredValueOfFirstCard() {
		return Card.ACE;
	}

	protected int requiredValueOfLastCard() {
		return Card.KING;
	}

	@Override
	protected int nextValue(Card card) {
		
		return card.getValue() + 1;
	}

	private AscendingSuitStackStrategy(Card.Suit suit) {
		super(suit);
	}

}
