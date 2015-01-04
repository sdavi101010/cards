package com.scottdavidson.cards.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to pretty print needed card displays.
 * 
 * @author scdavidson
 * 
 */
class PrettyPrint {

	public static String printFoundation(Foundation foundation) {

		// Get the top card of each stack
		List<Card> topCards = foundation.getTopCards();

		return printRowOfCards(topCards);
	}

	public static String printRowOfCards(List<Card> cards) {

		// Get the pretty print values for each card
		List<List<String>> prettyPrintCards = new ArrayList<List<String>>();

		// Iterate through the decks and get the pretty print of the top cards
		for (Card card : cards) {

			// Insert the pretty print version of each card
			if (null == card) {
				prettyPrintCards.add(Card.prettyPrintNullCard());
			} else {
				prettyPrintCards.add(card.prettyPrint());
			}
		}

		// Iterate through the pretty prints of the cards and collate each line
		// so that the cards are displayed next to each other
		int index = 0;
		boolean moreLines = true;
		StringBuilder builder = new StringBuilder();
		while (moreLines) {

			for (List<String> prettyPrintCard : prettyPrintCards) {
				builder.append("  ").append(prettyPrintCard.get(index));
			}

			// CR/LF
			builder.append(" \n");

			// Increment the index
			index += 1;

			// If index > number of lines, all done
			if (index >= prettyPrintCards.get(0).size()) {
				moreLines = false;
			}

		}

		// Return it ...
		return builder.toString();

	}

	public static String printRowOfStacks(List<CardStack> cardStacks) {

		// Get the pretty print values for each card stack
		List<List<String>> prettyPrintCardStacks = new ArrayList<List<String>>();

		// Iterate through the card stacks and get the pretty print for each
		for (CardStack cardStack : cardStacks) {

			prettyPrintCardStacks.add(cardStack.prettyPrint());

		}

		// Iterate through the pretty prints of the card stacks and collate each line
		// so that the cards are displayed next to each other
		int index = 0;
		boolean moreLines = true;
		StringBuilder builder = new StringBuilder();
		while (moreLines) {

			for (List<String> prettyPrintCardStack : prettyPrintCardStacks) {
				builder.append("  ").append(prettyPrintCardStack.get(index));
			}

			// CR/LF
			builder.append(" \n");

			// Increment the index
			index += 1;

			// If index > number of lines, all done
			if (index >= prettyPrintCardStacks.get(0).size()) {
				moreLines = false;
			}

		}

		// Return it ...
		return builder.toString();

	}

	// public static String printTableaus(Tableaus tableaus) {
	//
	// // Iterate through the stacks
	//
	// }
	//

}
