package com.scottdavidson.cards.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TableausTest {

	@Test
	public void testPrettyPrint() {

		// Test starting empty and adding
		{
			// Instantiate the Tableaus
			Tableaus tableaus = Tableaus.newTableaus();
			
			// Add cards
			tableaus.addCardToEmptyStack(Card.newCard(Card.ACE, Card.Suit.HEARTS));
			tableaus.addCardToEmptyStack(Card.newCard(5, Card.Suit.HEARTS));
			tableaus.addCardToEmptyStack(Card.newCard(9, Card.Suit.HEARTS));
			tableaus.addCardToEmptyStack(Card.newCard(Card.KING, Card.Suit.HEARTS));
			tableaus.addCardToEmptyStack(Card.newCard(2, Card.Suit.DIAMONDS));
			tableaus.addCardToEmptyStack(Card.newCard(6, Card.Suit.DIAMONDS));
			tableaus.addCardToEmptyStack(Card.newCard(10, Card.Suit.DIAMONDS));
			tableaus.addCardToEmptyStack(Card.newCard(3, Card.Suit.CLUBS));
			tableaus.addCardToEmptyStack(Card.newCard(7, Card.Suit.CLUBS));
			tableaus.addCardToEmptyStack(Card.newCard(Card.JACK, Card.Suit.CLUBS));
			tableaus.addCardToEmptyStack(Card.newCard(4, Card.Suit.SPADES));
			tableaus.addCardToEmptyStack(Card.newCard(8, Card.Suit.SPADES));
			tableaus.addCardToEmptyStack(Card.newCard(Card.QUEEN, Card.Suit.SPADES));
			tableaus.addCardToEmptyStack(Card.newCard(Card.QUEEN, Card.Suit.HEARTS));
			tableaus.addCardToEmptyStack(Card.newCard(Card.QUEEN, Card.Suit.CLUBS));
			tableaus.addCardToEmptyStack(Card.newCard(Card.QUEEN, Card.Suit.DIAMONDS));
			tableaus.addCardToEmptyStack(Card.newCard(Card.JACK, Card.Suit.SPADES));
			tableaus.addCardToEmptyStack(Card.newCard(Card.JACK, Card.Suit.HEARTS));
			tableaus.addCardToEmptyStack(Card.newCard(4, Card.Suit.CLUBS));
			tableaus.addCardToEmptyStack(Card.newCard(Card.JACK, Card.Suit.DIAMONDS));
			
			System.out.println("  ---------------  TABLEAUS --------------");
			System.out.println(tableaus.toString());

		}

		// Test providing full initialization
		{
			
			// Create list of initial tableau cards
			List<Card> initialTableauCards = new ArrayList<Card>();
			initialTableauCards.add(Card.newCard(Card.ACE, Card.Suit.HEARTS));
			initialTableauCards.add(Card.newCard(5, Card.Suit.HEARTS));
			initialTableauCards.add(Card.newCard(9, Card.Suit.HEARTS));
			initialTableauCards.add(Card.newCard(Card.KING, Card.Suit.HEARTS));
			initialTableauCards.add(Card.newCard(2, Card.Suit.DIAMONDS));
			initialTableauCards.add(Card.newCard(6, Card.Suit.DIAMONDS));
			initialTableauCards.add(Card.newCard(10, Card.Suit.DIAMONDS));
			initialTableauCards.add(Card.newCard(3, Card.Suit.CLUBS));
			initialTableauCards.add(Card.newCard(7, Card.Suit.CLUBS));
			initialTableauCards.add(Card.newCard(Card.JACK, Card.Suit.CLUBS));
			initialTableauCards.add(Card.newCard(4, Card.Suit.SPADES));
			initialTableauCards.add(Card.newCard(8, Card.Suit.SPADES));
			initialTableauCards.add(Card.newCard(Card.QUEEN, Card.Suit.SPADES));
			initialTableauCards.add(Card.newCard(Card.QUEEN, Card.Suit.HEARTS));
			initialTableauCards.add(Card.newCard(Card.QUEEN, Card.Suit.CLUBS));
			initialTableauCards.add(Card.newCard(Card.QUEEN, Card.Suit.DIAMONDS));
			initialTableauCards.add(Card.newCard(Card.JACK, Card.Suit.SPADES));
			initialTableauCards.add(Card.newCard(Card.JACK, Card.Suit.HEARTS));
			initialTableauCards.add(Card.newCard(4, Card.Suit.CLUBS));
			initialTableauCards.add(Card.newCard(Card.JACK, Card.Suit.DIAMONDS));
			
			// Instantiate the Tableaus
			Tableaus tableaus = Tableaus.newTableaus(initialTableauCards);
			
			// Add some more (for testing multiple cards in pretty print)
			tableaus.addCardToSpecifiedStack(Card.newCard(2, Card.Suit.HEARTS), 0);
			tableaus.addCardToSpecifiedStack(Card.newCard(3, Card.Suit.HEARTS), 0);

			tableaus.addCardToSpecifiedStack(Card.newCard(8, Card.Suit.HEARTS), 3);
			tableaus.addCardToSpecifiedStack(Card.newCard(7, Card.Suit.HEARTS), 3);
			tableaus.addCardToSpecifiedStack(Card.newCard(6, Card.Suit.HEARTS), 3);
			

			System.out.println("  ---------------  TABLEAUS --------------");
			System.out.println(tableaus.toString());

		}
	}

}
