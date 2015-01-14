package com.scottdavidson.cards.util;

import java.util.ArrayList;
import java.util.List;

public class Card implements Comparable<Card> {

	public enum Suit {
		HEARTS, DIAMONDS, SPADES, CLUBS
	}

	public final static int ACE = 1;
	public final static int JACK = 11;
	public final static int QUEEN = 12;
	public final static int KING = 13;
	public final static int JOKER = 14;

	private final int value;
	private final Suit suit;
	private final int hash;

	public static Card newCard(int value, Suit suit) {

		if (value > KING || value < ACE) {
			throw new IllegalArgumentException(
					"Illegal card value (1<= value <= 13) : " + value);
		}
		return new Card(value, suit);

	}

	public static Card newJoker() {
		return new Card(JOKER, Suit.CLUBS);
	}

	public int getValue() {
		return value;
	}

	public boolean isJoker() {
		return this.value == JOKER;
	}

	public String getValueAsString() {
		switch (this.value) {
		case ACE:
			return "Ace";
		case 2:
			return "2";
		case 3:
			return "3";
		case 4:
			return "4";
		case 5:
			return "5";
		case 6:
			return "6";
		case 7:
			return "7";
		case 8:
			return "8";
		case 9:
			return "9";
		case 10:
			return "10";
		case JACK:
			return "Jack";
		case QUEEN:
			return "Queen";
		case KING:
			return "King";
		case JOKER:
			return "Joker";
		default:
			return "King";
		}

	}

	public Suit getSuit() {
		return suit;
	}

	public String conciseToString() {
		return conciseToString(true);
	}

	public String conciseToString(boolean withCurlyBraces) {

		StringBuilder builder = new StringBuilder();

		// Opening curly brace (if requested)
		if (withCurlyBraces) {
			builder.append("{ ");
		}

		// If a Joker
		if (getValue() == JOKER) {
			builder.append(getValueAsString());
		} else if (getValue() == 10) {
			String valueAsConciseString = "X";
			String suitAtConciseString = getSuit().toString().substring(0, 1);
			builder.append(valueAsConciseString).append(":")
					.append(suitAtConciseString);
		} else {
			String valueAsConciseString = getValueAsString().substring(0, 1);
			String suitAtConciseString = getSuit().toString().substring(0, 1);
			builder.append(valueAsConciseString).append(":")
					.append(suitAtConciseString);
		}

		// Closing curly brace (if requested)
		if (withCurlyBraces) {
			builder.append(" }");
		}

		return builder.toString();

	}

	/**
	 * Generates a pretty version of the card for character display as follows:
	 * 
	 * @return character graphic representing the card in the form of an array
	 *         of lines.
	 */
	public List<String> prettyPrint() {

		List<String> result = new ArrayList<String>();

		// Top
		result.add("---------");

		// Row 2
		result.add("|       |");

		// Row 3 (w/ the value)
		result.add("|  " + conciseToString(false) + "  |");

		// Row 4
		result.add("|       |");

		// Bottom
		result.add("---------");

		return result;

	}

	public static List<String> prettyPrintNullCard() {

		List<String> result = new ArrayList<String>();

		// Top
		result.add("---------");

		// Row 2
		result.add("|       |");

		// Row 3 (w/ the value)
		result.add("|  ---  |");

		// Row 4
		result.add("|       |");

		// Bottom
		result.add("---------");

		return result;

	}

	@Override
	public String toString() {

		// If a Joker
		if (getValue() == JOKER) {
			return "{ " + getValueAsString() + " }";
		} else {
			return "{ " + getValueAsString() + " of " + getSuit() + " }";
		}
	}

	@Override
	public int compareTo(Card comparisonCard) {
		
		if ( this.hash == comparisonCard.hash ) {
			return 0;
		}
		else if ( this.hash > comparisonCard.hash ) {
			return 1;
		}
		else {
			return -1;
		}
	}

	protected int computeHash() {

		if (getValue() == JOKER) {
			return 0;
		} else {
			return this.value + this.suit.ordinal() * KING;
		}
	}

	private Card(int value, Suit suit) {
		this.value = value;
		this.suit = suit;
		this.hash = computeHash();
	}

}
