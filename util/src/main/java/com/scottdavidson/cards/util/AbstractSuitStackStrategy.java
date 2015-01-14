package com.scottdavidson.cards.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		if (null != topCard && this.suit != topCard.getSuit()) {
			throw new CardUtilException(
					"Top card's suit doesn't match strategy - logic error! Top Card Suit = "
							+ topCard.getSuit() + " , Strategy Suit: "
							+ this.suit);
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

		// Test 3 : if top card is null, candidate card must be the first valid
		// card value
		if (null == topCard) {
			if (candidateCard.getValue() == requiredValueOfFirstCard()) {
				return true;
			} else {
				return false;
			}
		}

		// For this abstract case, everything's good !
		return true;
	}

	@Override
	public List<Card> cardsThatCanBePlayed(Card topCard) {

		// Error check - the top card has to be in the appropriate suit
		if (null != topCard && this.suit != topCard.getSuit()) {
			throw new CardUtilException("Provided top card's suit ("
					+ topCard.getSuit() + ") doesn't match this strategy");
		}

		// If top card is null, return the required first card
		if (null == topCard) {
			return new ArrayList<Card>(Arrays.asList(Card.newCard(
					requiredValueOfFirstCard(), this.suit)));
		}

		// If top card is the last card that can be played, return empty list
		else if (topCard.getValue() == requiredValueOfLastCard()) {
			return new ArrayList<Card>();
		}
		
		else {
			return new ArrayList<Card>(Arrays.asList(Card.newCard(
					topCard.getValue() + 1, this.suit)));

		}
	}
	
	/**
	 * If top card is the required value of the last card, return true; false, otherwise.
	 */
	@Override
	public boolean isComplete(Card topCard) {
		return ( topCard.getValue() == requiredValueOfLastCard() );
	}

	/**
	 * Abstract method requiring the value of the first card for this strategy.
	 * 
	 * @return the value of the first card for this strategy
	 */
	protected abstract int requiredValueOfFirstCard();

	/**
	 * Abstract method requiring the value of the last card for this strategy.
	 * 
	 * @return the value of the last card for this strategy
	 */
	protected abstract int requiredValueOfLastCard();
	
	/**
	 * Abstract method requiring the next value of the card to be played on the provided card.
	 * @param card the card to be checked
	 * @return the next value of the card to be played on the provided card
	 */
	protected abstract int nextValue(Card card);	
	
	protected AbstractSuitStackStrategy(Card.Suit suit) {
		this.suit = suit;
	}

}
