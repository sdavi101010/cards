package com.scottdavidson.cards.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent an ordered range of cards in a particular suit. This class tracks
 * if there's a gap and determines the direction (ascending or descending) based
 * on the 2nd card inserted.
 * 
 * @author scdavidson
 * 
 */
public class CardRange {

	private List<Card> list = new ArrayList<Card>();
	private final Card.Suit suit;
	private Orientation direction;

	public static CardRange newCardRange(Card.Suit suit, Card initialCard) {

		if (null == suit || null == initialCard) {
			throw new CardUtilException(
					"A CardRange requires both a suit and initial card to be constructed");
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
		if (Orientation.NEUTRAL == this.direction) {
			return true;
		}

		// Case 2: Range is Ascending --> candidate must be lower than from
		else if (Orientation.ASCENDING == this.direction
				&& candidateCard.getValue() < getFromCard().getValue()) {
			return true;
		}

		// Case 3: Range is Descending --> candidate must be higher than to
		else if (Orientation.DESCENDING == this.direction
				&& candidateCard.getValue() > getToCard().getValue()) {
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

		// Case 1: Range is Neutral (meaning there's only one Card)
		if (Orientation.NEUTRAL == this.direction) {

			// Add the candidate to the range and use it to determine the
			// direction
			if (candidateCard.getValue() < this.getFromCard().getValue()) {
				this.direction = Orientation.ASCENDING;
				this.list.add(0, candidateCard);
			} else {
				this.direction = Orientation.DESCENDING;
				this.list.add(candidateCard);
			}
		}

		// Case 2: Range is Ascending
		else if (Orientation.ASCENDING == this.direction) {
			this.list.add(0, candidateCard);

		}

		// Case 2: Range is Descending
		else {
			this.list.add(candidateCard);
		}

		// Return the candidate
		return candidateCard;

	}

	public boolean contains(Card card) {
		return this.list.contains(card);
	}

	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	public Card remove(Card card) {

		// If NEUTRAL or ASCENDING, confirm the first card is the one and then
		// remove it
		if (Orientation.NEUTRAL == this.direction
				|| Orientation.ASCENDING == this.direction) {
			if (card != getFromCard()) {
				throw new CardUtilException("Removing " + card.prettyPrint()
						+ " from ASCENDING Range but from card doesn't match"
						+ this.toString());
			}

			// Remove the first card
			this.list.remove(0);
		} else {
			if (card != getToCard()) {
				throw new CardUtilException("Removing " + card.prettyPrint()
						+ " from DESCENDING Range but to card doesn't match"
						+ this.toString());
			}

			// Remove the last card
			this.list.remove(this.list.size() - 1);

		}

		return card;
	}

	public Card.Suit getSuit() {
		return suit;
	}

	public Card getFromCard() {
		return this.list.get(0);
	}

	public Card getToCard() {
		return this.list.get(this.list.size() - 1);
	}

	public Orientation getDirection() {
		return direction;
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append("Direction --> " + this.direction).append("\n");
		builder.append(
				"[ " + getFromCard().conciseToString(false) + " , "
						+ getToCard().conciseToString(false) + " ]").append(
				"\n");
		return builder.toString();
	}

	private CardRange(Card.Suit suit, Card initialCard) {
		this.suit = suit;
		this.direction = Orientation.NEUTRAL;
		this.list.add(initialCard);
	}

}
