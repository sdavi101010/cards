package com.scottdavidson.cards.util;

import java.util.List;

import org.junit.Test;

public class CardTest {

	@Test
	public void testPrettyPrint() {

		Card fourOfHearts = Card.newCard(4, Card.Suit.HEARTS);
		System.out.println("4 of hearts -- pretty print -- ");
		List<String> prettyPrint = fourOfHearts.prettyPrint();
		for (String line : prettyPrint) {

			System.out.println(line);
		}

	}

}
