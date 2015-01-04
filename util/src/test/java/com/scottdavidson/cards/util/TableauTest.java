package com.scottdavidson.cards.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

public class TableauTest {
	
	private final static Card KING_DIAMONDS = Card.newCard(Card.KING, Card.Suit.DIAMONDS);
	private final static Card QUEEN_DIAMONDS = Card.newCard(Card.QUEEN, Card.Suit.DIAMONDS);
	private final static Card JACK_DIAMONDS = Card.newCard(Card.JACK, Card.Suit.DIAMONDS);
	private final static Card TEN_DIAMONDS = Card.newCard(10, Card.Suit.DIAMONDS);
	private final static Card ACE_DIAMONDS = Card.newCard(Card.ACE, Card.Suit.DIAMONDS);
	private final static Card THREE_DIAMONDS = Card.newCard(3, Card.Suit.DIAMONDS);
	private final static Card FOUR_DIAMONDS = Card.newCard(4, Card.Suit.DIAMONDS);
	private final static Card FOUR_CLUBS = Card.newCard(4, Card.Suit.CLUBS);
	private final static Card ACE_SPADES = Card.newCard(Card.ACE, Card.Suit.SPADES);


//	@Test(expected = CardUtilException.class)
//	public void testNullCardConstructor() {
//		CardRange.newCardRange(Card.Suit.DIAMONDS, null);
//	}
//
//	@Test(expected = CardUtilException.class)
//	public void testNullSuitConstructor() {
//		CardRange.newCardRange(null,
//				Card.newCard(Card.KING, Card.Suit.DIAMONDS));
//	}
//
	
	@Test
	public void testCardRange() {

//		// No range test
//		{
//			Tableau tableau = Tableau.newTableau();
//			System.out.println(tableau.debugPrintRanges());
//		}
//		
//		// Neutral test
//		{
//			Tableau tableau = Tableau.newTableau(TEN_DIAMONDS);
//			System.out.println(tableau.debugPrintRanges());
//		}
//		
//		// (Single) Ascending test
//		{
//			Tableau tableau = Tableau.newTableau(TEN_DIAMONDS);
//			tableau.addCard(FOUR_DIAMONDS);
//			tableau.addCard(THREE_DIAMONDS);
//			System.out.println(tableau.debugPrintRanges());
//		}
//		
//		// (Single) Descending test
//		{
//			Tableau tableau = Tableau.newTableau(THREE_DIAMONDS);
//			tableau.addCard(FOUR_DIAMONDS);
//			tableau.addCard(JACK_DIAMONDS);
//			tableau.addCard(KING_DIAMONDS);
//			System.out.println(tableau.debugPrintRanges());
//		}
//		
//		// (Double) Descending and Neutral test
//		{
//			Tableau tableau = Tableau.newTableau(THREE_DIAMONDS);
//			tableau.addCard(FOUR_DIAMONDS);
//			tableau.addCard(JACK_DIAMONDS);
//			tableau.addCard(KING_DIAMONDS);
//			tableau.addCard(QUEEN_DIAMONDS);
//			System.out.println(tableau.debugPrintRanges());
//		}
//
//		// (Triple) Descending and Double Neutral test
//		{
//			Tableau tableau = Tableau.newTableau(THREE_DIAMONDS);
//			tableau.addCard(FOUR_DIAMONDS);
//			tableau.addCard(QUEEN_DIAMONDS);
//			tableau.addCard(KING_DIAMONDS);
//			tableau.addCard(ACE_SPADES);
//			tableau.addCard(JACK_DIAMONDS);
//			System.out.println(tableau.debugPrintRanges());
//		}

		// Duplicate Neutral test
		{
			Tableau tableau = Tableau.newTableau(THREE_DIAMONDS);
			tableau.addCard(ACE_SPADES);
			tableau.addCard(FOUR_CLUBS);
			tableau.addCard(ACE_SPADES);
			System.out.println(tableau.debugPrintRanges());
		}

	}

}
