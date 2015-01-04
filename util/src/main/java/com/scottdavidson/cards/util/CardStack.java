package com.scottdavidson.cards.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * A simple stack to manage Cards for card games. An optional strategy can be
 * applied to limit which cards can be placed onto the stack.
 * 
 * @author scdavidson
 * 
 */
public class CardStack extends Stack<Card> {

	private static final long serialVersionUID = -9141888250129175888L;

	private final StackStrategy stackStrategy;

	public static CardStack newCardStack() {
		return newCardStack(NullStackStrategy.newNullStackStrategy());
	}

	public static CardStack newCardStack(StackStrategy stackStrategy) {
		return new CardStack(stackStrategy);
	}
	
	public static CardStack newCardStack(CardStack original) {
		
		// Get the elements from the original
		Object[] originalAsObjectArray = original.toArray();
		
		// Iterate through them (in reverse order) and push them onto a new CardStack
		CardStack copy = newCardStack(original.stackStrategy);
		for (int i = 1; i <= originalAsObjectArray.length; i++) {
			copy.add((Card) originalAsObjectArray[originalAsObjectArray.length - i]);
		}
		
		// Return the copy
		return copy;
	}

	public boolean cardCanBePushed(Card candidateCard) {

		return this.stackStrategy.cardCanBePushed(topCard(), candidateCard);
	}

	@Override
	public Card push(Card card) {

		// Error check
		if (this.stackStrategy.cardCanBePushed(topCard(), card)) {
			return super.push(card);
		} else {
			return null;
		}
	}

	public Card topCard() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.peek();
		}
	}

	/**
	 * Generates a pretty version of the card stack for character display as
	 * follows:
	 * 
	 * @return character graphic representing the card in the form of an array
	 *         of lines.
	 */
	public List<String> prettyPrint() {

		List<String> result = new ArrayList<String>();
		
		// Copy this stack (Cards are immutable :-)) so we can operate on it
		CardStack copy = newCardStack(this);
		
		// Top
		result.add("---------");

		// Row 2
		//
		// If there are 2 or more cards in the stack, put the top one in row 2
		if (copy.size() >= 2) {
			Card topCard = copy.pop();
			result.add("| " + topCard.conciseToString(false) + "   |");
		} else {
			result.add("|       |");
		}

		// Row 3 
		//
		// If there is a card, display it in the middle, otherwise display "---"
		if ( copy.size() >= 1) {
		result.add("|  " + copy.pop().conciseToString(false) + "  |");
		}
		else {
			result.add("|  " + "---" + "  |");			
		}

		// Row 4
		//
		// There are three options here: 
		//   -- if >= 2, then use concise (shifted) and add a "+" to indicate even more
		//   -- if 1 left, then use concise (shifted)
		//   -- if 0, blank
		if (copy.size() >= 2) {
			Card topCard = copy.pop();
			result.add("|++ " + topCard.conciseToString(false) + " |");
		} else if ( copy.size() == 1) {
			Card topCard = copy.pop();
			result.add("|   " + topCard.conciseToString(false) + " |");			
		} else {
			result.add("|       |");
		}

		// Bottom
		result.add("---------");

		return result;

	}

	private CardStack(StackStrategy stackStrategy) {
		this.stackStrategy = stackStrategy;
	}

}
