package com.scottdavidson.cards.util;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

public class CardRangeTest {
	
	private final static Card KING_DIAMONDS = Card.newCard(Card.KING, Card.Suit.DIAMONDS);
	private final static Card QUEEN_DIAMONDS = Card.newCard(Card.QUEEN, Card.Suit.DIAMONDS);
	private final static Card JACK_DIAMONDS = Card.newCard(Card.JACK, Card.Suit.DIAMONDS);
	private final static Card TEN_DIAMONDS = Card.newCard(10, Card.Suit.DIAMONDS);
	private final static Card ACE_DIAMONDS = Card.newCard(Card.ACE, Card.Suit.DIAMONDS);
	private final static Card THREE_DIAMONDS = Card.newCard(3, Card.Suit.DIAMONDS);
	private final static Card FOUR_DIAMONDS = Card.newCard(4, Card.Suit.DIAMONDS);


	@Test(expected = CardUtilException.class)
	public void testNullCardConstructor() {
		CardRange.newCardRange(Card.Suit.DIAMONDS, null);
	}

	@Test(expected = CardUtilException.class)
	public void testNullSuitConstructor() {
		CardRange.newCardRange(null,
				Card.newCard(Card.KING, Card.Suit.DIAMONDS));
	}

	@Test
	public void testCardRange() {

		// Neutral test
		{
			CardRange range = CardRange.newCardRange(Card.Suit.DIAMONDS,
					KING_DIAMONDS);
			Assert.assertEquals(KING_DIAMONDS, range.getFromCard());
			Assert.assertEquals(KING_DIAMONDS, range.getToCard());
		}

		// Ascending test
		{
			{
				CardRange range = CardRange.newCardRange(Card.Suit.DIAMONDS,
						KING_DIAMONDS);
				assertTrue(range.canAddCard(JACK_DIAMONDS));
				range.addCard(JACK_DIAMONDS);
				assertEquals(JACK_DIAMONDS, range.getFromCard());
				assertEquals(KING_DIAMONDS, range.getToCard());
			}
			{
				CardRange range = CardRange.newCardRange(Card.Suit.DIAMONDS,
						QUEEN_DIAMONDS);
				assertTrue(range.canAddCard(JACK_DIAMONDS));
				range.addCard(JACK_DIAMONDS);
				assertEquals(JACK_DIAMONDS, range.getFromCard());
				assertEquals(QUEEN_DIAMONDS, range.getToCard());
				range.addCard(TEN_DIAMONDS);
				assertEquals(TEN_DIAMONDS, range.getFromCard());
				assertEquals(QUEEN_DIAMONDS, range.getToCard());
				range.addCard(FOUR_DIAMONDS);
				assertEquals(FOUR_DIAMONDS, range.getFromCard());
				assertEquals(QUEEN_DIAMONDS, range.getToCard());
			}
			{
				CardRange range = CardRange.newCardRange(Card.Suit.DIAMONDS,
						KING_DIAMONDS);
				assertTrue(range.canAddCard(JACK_DIAMONDS));
				range.addCard(JACK_DIAMONDS);
				assertFalse(range.canAddCard(QUEEN_DIAMONDS));
			}
			
		}

		// Descending test
		{
			{
				CardRange range = CardRange.newCardRange(Card.Suit.DIAMONDS,
						ACE_DIAMONDS);
				assertTrue(range.canAddCard(FOUR_DIAMONDS));
				range.addCard(FOUR_DIAMONDS);
				assertEquals(ACE_DIAMONDS, range.getFromCard());
				assertEquals(FOUR_DIAMONDS, range.getToCard());
				assertEquals(Orientation.DESCENDING, range.getDirection());
			}
			{
				CardRange range = CardRange.newCardRange(Card.Suit.DIAMONDS,
						THREE_DIAMONDS);
				assertTrue(range.canAddCard(FOUR_DIAMONDS));
				range.addCard(FOUR_DIAMONDS);
				assertEquals(THREE_DIAMONDS, range.getFromCard());
				assertEquals(FOUR_DIAMONDS, range.getToCard());
				range.addCard(TEN_DIAMONDS);
				assertEquals(THREE_DIAMONDS, range.getFromCard());
				assertEquals(TEN_DIAMONDS, range.getToCard());
				range.addCard(QUEEN_DIAMONDS);
				assertEquals(THREE_DIAMONDS, range.getFromCard());
				assertEquals(QUEEN_DIAMONDS, range.getToCard());
			}
			{
				CardRange range = CardRange.newCardRange(Card.Suit.DIAMONDS,
						FOUR_DIAMONDS);
				assertTrue(range.canAddCard(JACK_DIAMONDS));
				range.addCard(JACK_DIAMONDS);
				assertFalse(range.canAddCard(TEN_DIAMONDS));
			}
		}

	}

}
