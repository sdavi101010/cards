package com.scottdavidson.cards.util;

import java.util.ArrayList;
import java.util.List;


/**
 * In many solitaire games, the Tableau is a stack of cards with no specific order (think
 * discard pile); in Colorado Solitaire, the Tableau is consistent with this concept and cards
 * may be played off the top. This Tableau actually provides some convenience services by
 * keeping track of ordered card ranges (throughout the overall Tableau).
 * 
 * @author scdavidson
 * 
 */
public class Tableau {

	private CardStack stack;
	private List<CardRange> ranges;

	public static Tableau newTableau(Card initialCard) {

		Tableau returnTableau = newTableau();
		returnTableau.addCard(initialCard);
		return returnTableau;
	}
	
	public static Tableau newTableau() {
		Tableau returnTableau = new Tableau();
		return returnTableau;
	}
	
	public boolean isEmpty() {
		return this.stack.isEmpty();
	}
	
	public CardStack getStack() {
		
		// TODO - should consider a defensive copy
		return this.stack;
	}
	
	public Card addCard(Card card) {
		
		// Error check
		if ( null == card ) {
			return null;
		}
		
		// Push the card on top of the stack
		this.stack.push(card);

		// Check to see if the card can be put into an existing range
		boolean foundExistingRange = false;
		for ( CardRange range : this.ranges ) {
			
			// Check to see if card can be added
			if ( range.canAddCard(card)) {
				
				// Add it
				range.addCard(card);
				foundExistingRange = true;
			}
		}
		
		// If an existing range didn't work, create a new range w/ the card
		if ( !foundExistingRange ) {
			this.ranges.add(CardRange.newCardRange(card.getSuit(), card));
		}
		
		// Return the call (per the standard stack protocol)
		return card;
		
	}
	
	public Card topCard() {
		if ( this.stack.isEmpty() ) {
			return null;
		}
		else {
			return this.stack.peek();
		}
	}
	
	public Card removeTopCard() {
		if ( this.stack.isEmpty() ) {
			return null;
		}
		else {
			return this.stack.pop();
		}
	}
	
	public List<String> prettyPrint() {
		return this.stack.prettyPrint();
	}
	
	public String debugPrintRanges() {
		
		if ( this.ranges.isEmpty() ) {
			return " --- no ranges --- ";
		}
		
		StringBuilder builder = new StringBuilder();
		
		int index = 0;
		for ( CardRange range : this.ranges ) {
			builder.append("RANGE (" + index + ") \n");
			builder.append(range.toString()).append("\n");
			index += 1;
		}
		
		return builder.toString();
	}
	
	private Tableau() {
		
		// Instantiate the stack
		this.stack = CardStack.newCardStack(NullStackStrategy.newNullStackStrategy());
		
		// Instantiate the ranges
		this.ranges = new ArrayList<CardRange>();
		
	}
}
