package com.scottdavidson.cards.util;

public class DescendingSuitStackStrategy extends AbstractSuitStackStrategy {

	public static DescendingSuitStackStrategy newDescendingSuitStackStrategy(
			Card.Suit suit) {
		return new DescendingSuitStackStrategy(suit);
	}

	@Override
	public boolean cardCanBePushed(Card topCard, Card candidateCard) {

		// Let the abstract parent do its check first ...
		if (!super.cardCanBePushed(topCard, candidateCard)) {
			return false;
		}

		// No other tests (yet)
		return true;
	}

	@Override
	protected int requiredValueOfFirstCard() {
		return Card.KING;
	}

	@Override
	protected int requiredValueOfLastCard() {
		return Card.ACE;
	}

	@Override
	protected int nextValue(Card card) {

		return card.getValue() - 1;
	}

	private DescendingSuitStackStrategy(Card.Suit suit) {
		super(suit);
	}

}
