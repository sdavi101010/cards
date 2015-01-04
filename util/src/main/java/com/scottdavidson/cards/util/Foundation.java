package com.scottdavidson.cards.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Not sure this is a standard definition, but for our purposes, a foundation is
 * a set of 4 stacks, one for each suit. The foundation can be either ASC or
 * DESC.
 * 
 * @author scdavidson
 * 
 */
public class Foundation {

	private final List<CardStack> stacks = new ArrayList<CardStack>(4);

	public static Foundation newAscendingFoundation() {
		return new Foundation(
				AscendingSuitStackStrategy
						.newAscendingSuitStackStrategy(Card.Suit.HEARTS),
				AscendingSuitStackStrategy
						.newAscendingSuitStackStrategy(Card.Suit.DIAMONDS),
				AscendingSuitStackStrategy
						.newAscendingSuitStackStrategy(Card.Suit.SPADES),
				AscendingSuitStackStrategy
						.newAscendingSuitStackStrategy(Card.Suit.CLUBS));
	}

	public static Foundation newDescendingFoundation() {
		return new Foundation(
				DescendingSuitStackStrategy
						.newDescendingSuitStackStrategy(Card.Suit.HEARTS),
				DescendingSuitStackStrategy
						.newDescendingSuitStackStrategy(Card.Suit.DIAMONDS),
				DescendingSuitStackStrategy
						.newDescendingSuitStackStrategy(Card.Suit.SPADES),
				DescendingSuitStackStrategy
						.newDescendingSuitStackStrategy(Card.Suit.CLUBS));
	}

	/**
	 * Returns a cloned version of this Foundation.
	 * 
	 * @return a cloned version of this Foundation
	 */
	public Foundation cloneFoundation() {
		
		// Copy each of the stacks 
		List<CardStack> clonedStacks = new ArrayList<CardStack>();
		for ( CardStack stack : this.stacks ) {
			clonedStacks.add(CardStack.newCardStack(stack));
		}
		
		// Instantiate a new instance of Foundation and return it
		return new Foundation(clonedStacks);

	}
	
	public Card playCardOnto(Card card) {

		// Error check
		CardStack stack = identifyCardStackThatCanBePlayedOnto(card);
		if (null == stack) {
			return null;
		}

		// Add the card to the appropriate stack
		stack.add(card);
		return card;
	}

	public boolean cardCanBePlayedOnto(Card card) {

		CardStack stack = identifyCardStackThatCanBePlayedOnto(card);
		if (null == stack) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Use the PrettyPrint helper class to get a character graphic of the
	 * foundation
	 */
	@Override
	public String toString() {
		
		return PrettyPrint.printFoundation(this);

	}

	public List<Card> getTopCards() {

		List<Card> topCards = new ArrayList<Card>();

		// Iterate through the decks and pull off the top card
		for (CardStack stack : this.stacks) {
			topCards.add(stack.topCard());
		}

		return topCards;
	}

	protected CardStack identifyCardStackThatCanBePlayedOnto(Card card) {

		// Check to see if the card can be played on any of the stacks
		for (CardStack stack : stacks) {
			if (stack.cardCanBePushed(card)) {
				return stack;
			}
		}
		return null;

	}


	private Foundation(StackStrategy heartsStackStrategy,
			StackStrategy diamondsStackStrategy,
			StackStrategy spadesStackStrategy, StackStrategy clubsStackStrategy) {

		// Initialize the foundation w/ 4 Card Decks (of each suit, using the
		// provided strategy)
		this.stacks.add(CardStack.newCardStack(heartsStackStrategy));
		this.stacks.add(CardStack.newCardStack(diamondsStackStrategy));
		this.stacks.add(CardStack.newCardStack(spadesStackStrategy));
		this.stacks.add(CardStack.newCardStack(clubsStackStrategy));
	}

	private Foundation(List<CardStack> initializedStacks) {
		
		// Extract out the individual CardStacks from the initialized stack argument
		for ( CardStack initializedStack : initializedStacks ) {
			this.stacks.add(initializedStack);
		}
	}
	
}
