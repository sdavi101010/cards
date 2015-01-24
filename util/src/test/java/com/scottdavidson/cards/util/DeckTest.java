package com.scottdavidson.cards.util;

import org.junit.Test;

public class DeckTest {

	@Test
	public void testBasicDeck() {

		// Deck deck = Deck.newShuffledDeckWithJokers();
		// System.out.println("DECK \n" + deck.show());

		Deck deck;
		{
			deck = Deck.newShuffledDoubleDeckWithJokers();
			System.out.println("DOUBLE DECK \n" + deck.show());
		}

		try {

			while (true) {
				System.out.println(deck.dealCard().toString());
			}

		} catch (IllegalStateException ise) {
			System.out.println(" -- done -- ");
		}

		deck.shuffle();
		System.out.println("SHUFFLED DECK \n" + deck.show());
		System.out.println("SHUFFLED DECK \n" + deck.conciseShow());

	}

}
