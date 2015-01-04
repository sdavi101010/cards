package com.scottdavidson.cards.util;

/**
 * Represent an ordered range of cards in a particular suit. This class tracks
 * if there's a gap and determines the direction (ascending or descending) based
 * on the 2nd card inserted.
 * 
 * @author scdavidson
 * 
 */
public class CardRange {

	public enum Direction {
		NEUTRAL, ASCENDING, DESCENDING
	}

	private Card.Suit suit;
	private Card initialCard;
	private Card fromCard;
	private Card toCard;
	private Direction direction;

	public static CardRange newCardRange(Card.Suit suit, Card initialCard) {
		
		if ( null == suit || null == initialCard ) {
			throw new CardUtilException("A CardRange requires both a suit and initial card to be constructed");
		}
		return new CardRange(suit, initialCard);
	}

	/**
	 * Determine if the candidate card can be added to the range
	 * 
	 * @param candidateCard
	 * @return
	 */
	public boolean canAddCard(Card candidateCard) {

		// Card must be correct suit
		if (this.suit != candidateCard.getSuit()) {
			return false;
		}

		// Case 1: Range is Neutral --> can be added!
		if (Direction.NEUTRAL == this.direction) {
			return true;
		}

		// Case 2: Range is Ascending --> candidate must be lower than from
		else if (Direction.ASCENDING == this.direction
				&& candidateCard.getValue() < this.fromCard.getValue()) {
			return true;
		}

		// Case 3: Range is Descending --> candidate must be higher than to
		else if (Direction.DESCENDING == this.direction
				&& candidateCard.getValue() > this.toCard.getValue()) {
			return true;
		}

		// Otherwise, nope!
		else {
			return false;
		}

	}

	/**
	 * Add the candidate card to the range
	 * 
	 * @param candidateCard
	 * @return
	 */
	public Card addCard(Card candidateCard) {

		// Error check - make sure it can be added
		if (!canAddCard(candidateCard)) {
			return null;
		}

		// Case 1: Range is Neutral
		if (Direction.NEUTRAL == this.direction) {

			// Add the candidate to the range and use it to determine the
			// direction
			if (candidateCard.getValue() < this.initialCard.getValue()) {
				this.direction = Direction.ASCENDING;
				this.fromCard = candidateCard;
				this.toCard = initialCard;
			} else {
				this.direction = Direction.DESCENDING;
				this.fromCard = initialCard;
				this.toCard = candidateCard;
			}
		}

		// Case 2: Range is Ascending
		else if (Direction.ASCENDING == this.direction) {
			this.fromCard = candidateCard;

		}

		// Case 2: Range is Descending
		else {
			this.toCard = candidateCard;

		}

		// Return the candidate
		return candidateCard;

	}

	public Card.Suit getSuit() {
		return suit;
	}

	public Card getFromCard() {
		return fromCard;
	}

	public Card getToCard() {
		return toCard;
	}

	public Direction getDirection() {
		return direction;
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("Direction --> " + this.direction).append("\n");
		builder.append(
				"[ " + fromCard.conciseToString(false) + " , "
						+ toCard.conciseToString(false) + " ]").append("\n");
		return builder.toString();
	}

	private CardRange(Card.Suit suit, Card initialCard) {
		this.suit = suit;
		this.initialCard = initialCard;
		this.fromCard = initialCard;
		this.toCard = initialCard;
		this.direction = Direction.NEUTRAL;
	}

}
