package com.scottdavidson.cards.util;

import java.util.List;

import org.junit.Test;

public class FoundationTest {

	@Test
	public void testPrettyPrint() {

		// Test basics (no nulls)
		{
			// Instantiate the Foundation (ASC)
			Foundation foundation = Foundation.newAscendingFoundation();

			// Insert cards into the Foundation from each suit
			// HEARTS, DIAMONDS, SPADES, CLUBS
			for (int value = 1; value < 7; value++) {
				Card card = Card.newCard(value, Card.Suit.HEARTS);
				foundation.playCardOnto(card);
			}
			
			for (int value = 1; value < 5; value++) {
				Card card = Card.newCard(value, Card.Suit.DIAMONDS);
				foundation.playCardOnto(card);
			}
			
			for (int value = 1; value < 2; value++) {
				Card card = Card.newCard(value, Card.Suit.SPADES);
				foundation.playCardOnto(card);
			}
			
			for (int value = 1; value < 12; value++) {
				Card card = Card.newCard(value, Card.Suit.CLUBS);
				foundation.playCardOnto(card);
			}
			
			System.out.println("  ---------------  FOUNDATION --------------");
			System.out.println(foundation.toString());

		}

		// Test with nulls
		{
			// Instantiate the Foundation (ASC)
			Foundation foundation = Foundation.newAscendingFoundation();

			// Insert cards into the Foundation from each suit
			// HEARTS, DIAMONDS, SPADES, CLUBS
			for (int value = 1; value < 7; value++) {
				Card card = Card.newCard(value, Card.Suit.HEARTS);
				foundation.playCardOnto(card);
			}
						
			for (int value = 1; value < 12; value++) {
				Card card = Card.newCard(value, Card.Suit.CLUBS);
				foundation.playCardOnto(card);
			}
			
			System.out.println("  ---------------  FOUNDATION --------------");
			System.out.println(foundation.toString());

		}
	}

}
