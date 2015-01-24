package com.scottdavidson.cards.util;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CardTest {

	@Test
	public void testPrettyPrint() {

		Card fourOfHearts = Card.newCard(4, Card.Suit.HEARTS);
		List<String> prettyPrint = fourOfHearts.prettyPrint();
		for (String line : prettyPrint) {

			System.out.println(line);
		}

	}
	
	@Test
	public void testGetValueAndSuit() {
		
		Card fourOfHearts = Card.newCard(4, Card.Suit.HEARTS);
		Assert.assertEquals(4, fourOfHearts.getValue());
		Assert.assertEquals(Card.Suit.HEARTS, fourOfHearts.getSuit());
		
	}

	@Test
	public void testIsJoker() {
		Card joker = Card.newJoker();
		Assert.assertTrue(joker.isJoker());
		Card fourOfHearts = Card.newCard(4, Card.Suit.HEARTS);
		Assert.assertTrue(!fourOfHearts.isJoker());
		
	}

}
