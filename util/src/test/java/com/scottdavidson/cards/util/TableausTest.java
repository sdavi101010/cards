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

	}

}
