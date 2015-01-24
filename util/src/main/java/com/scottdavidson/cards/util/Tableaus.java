package com.scottdavidson.cards.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * In Colorado Solitaire, there are 20 stacks or tableaus; this class represents
 * those.
 * 
 * @author scdavidson
 * 
 */
public class Tableaus {

	private final static int NUMBER_OF_STACKS_IN_COLORADO_SOLITAIRE = 20;
	private Tableau tableaus[] = new Tableau[NUMBER_OF_STACKS_IN_COLORADO_SOLITAIRE];
	public static Tableaus newTableaus(List<Card> initialTableauCards) {

		// Error Check - must be 20 cards in list
		if (initialTableauCards.size() != NUMBER_OF_STACKS_IN_COLORADO_SOLITAIRE) {
			throw new CardUtilException("To construct a Tableaus, a list of "
					+ NUMBER_OF_STACKS_IN_COLORADO_SOLITAIRE
					+ " cards must be provided;"
					+ "the provided list has only "
					+ initialTableauCards.size() + " cards.");
		}

		return new Tableaus(initialTableauCards);
	}
	
	public static Tableaus newTableaus() {
		return new Tableaus();
	}
	
	public List<Tableau> getIndexedTableaus(Card card) {
		return this.cardToTableauMap.get(card);
	}
	
	public Card addCardToEmptyStack(Card card) {
		
		// Find the empty stack
		int index = emptyStack();
		
		// Add the card
		if ( -1 == index ) {
			return null;
		}
		else {
			
			// Add the card to the empty tableau
			this.tableaus[index].addCard(card);
			
			// Add the card and tableau to the map
			List<Tableau> tableauList = this.cardToTableauMap.get(card);
			if ( null == tableauList ) {
				tableauList = new ArrayList<Tableau>();
				this.cardToTableauMap.put(card, tableauList);
			}
			tableauList.add(this.tableaus[index]);
			
			return card;
		}
	}
	
	private Map<Card,List<Tableau>> cardToTableauMap = new TreeMap<Card,List<Tableau>>();

	public Card addCardToSpecifiedStack(Card card, int index) {
		
		// Remove the map entry for the referenced tableau (via the index)
		//
		// First, find the tableau in the map
		ColoradoTableauCard currentTopCard = this.tableaus[index].topCard();
		List<Tableau> tableauList = this.cardToTableauMap.get(currentTopCard);
		
		// Remove the tableau
		tableauList.remove(this.tableaus[index]);
		
		// Add the new card and tableau to the map
		tableauList = this.cardToTableauMap.get(card);
		if ( null == tableauList ) {
			tableauList = new ArrayList<Tableau>();
			this.cardToTableauMap.put(card, tableauList);
		}
		tableauList.add(this.tableaus[index]);
		
		// Add the card to the specified tableau
		return this.tableaus[index].addCard(card);
	}
	
	public List<ColoradoTableauCard> getTopCards() {
		
		List<ColoradoTableauCard> topCards = new ArrayList<ColoradoTableauCard>();
		for ( Tableau tableau : this.tableaus ) {
			topCards.add(tableau.topCard());
		}
		
		return topCards;
	}
	
	/**
	 * Find the empty stack (except for the end of the game, there should only
	 * ever be one). Return -1 if there are no empty ones (this shouldn't happen).
	 *  
	 * @return the index of the empty stack or -1 if there are none.
	 */
	protected int emptyStack() {
		
		for ( int index = 0; index < NUMBER_OF_STACKS_IN_COLORADO_SOLITAIRE; index++ ) {
			if ( tableaus[index].isEmpty() ) {
				return index;
			}
		}
		
		return -1;
	}

	@Override
	public String toString() {

		// Define number of rows to use
		int NUMBER_OF_ROWS = 4;

		StringBuilder builder = new StringBuilder();
		for (int row = 0; row < NUMBER_OF_ROWS; row++) {

			// Calculate the number of columns per row
			int COLUMNS_PER_ROW = NUMBER_OF_STACKS_IN_COLORADO_SOLITAIRE
					/ NUMBER_OF_ROWS;

			// Define the CardStack to be used in PrettyPrint
			List<CardStack> cardStack = new ArrayList<CardStack>();

			for (int column = 0; column < COLUMNS_PER_ROW; column++) {

				int index = row * COLUMNS_PER_ROW + column;
				cardStack.add(tableaus[index].getStack());
			}

			// Pretty print the cards in this row
			builder.append(PrettyPrint.printRowOfStacks(cardStack));
			
			// Add some blank space between rows
			builder.append("\n").append("\n").append("\n");
		}

		return builder.toString();
	}

	private Tableaus(List<Card> initialTableauCards) {

		int index = 0;
		for (Card card : initialTableauCards) {
			tableaus[index] = Tableau.newTableau(card);
			index += 1;
		}
	}

	private Tableaus() {

		for (int index = 0; index < NUMBER_OF_STACKS_IN_COLORADO_SOLITAIRE; index++) {
			tableaus[index] = Tableau.newTableau();
		}
	}
}
