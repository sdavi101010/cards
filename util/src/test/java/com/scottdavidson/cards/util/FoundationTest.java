package com.scottdavidson.cards.util;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.scottdavidson.cards.util.Card.Suit;

public class FoundationTest {

	private static Foundation ASCENDING_FOUNDATION;
	private static Foundation ASCENDING_FOUNDATION_NO_HEARTS;

	@BeforeClass
	public static void beforeClass() {

		{
			// Instantiate and initialize ASCENDING_FOUNDATION
			ASCENDING_FOUNDATION = Foundation.newAscendingFoundation();

			// Insert cards into the Foundation from each suit
			// HEARTS, DIAMONDS, SPADES, CLUBS
			for (int value = 1; value < 7; value++) {
				Card card = Card.newCard(value, Card.Suit.HEARTS);
				ASCENDING_FOUNDATION.playCardOnto(card);
			}

			for (int value = 1; value < 5; value++) {
				Card card = Card.newCard(value, Card.Suit.DIAMONDS);
				ASCENDING_FOUNDATION.playCardOnto(card);
			}

			for (int value = 1; value < 2; value++) {
				Card card = Card.newCard(value, Card.Suit.SPADES);
				ASCENDING_FOUNDATION.playCardOnto(card);
			}

			for (int value = 1; value < 12; value++) {
				Card card = Card.newCard(value, Card.Suit.CLUBS);
				ASCENDING_FOUNDATION.playCardOnto(card);
			}
		}

		{
			// Instantiate and initialize ASCENDING_FOUNDATION
			ASCENDING_FOUNDATION_NO_HEARTS = Foundation
					.newAscendingFoundation();

			// Insert cards into the Foundation from each suit
			// (No) HEARTS, DIAMONDS, SPADES, CLUBS
			for (int value = 1; value < 5; value++) {
				Card card = Card.newCard(value, Card.Suit.DIAMONDS);
				ASCENDING_FOUNDATION_NO_HEARTS.playCardOnto(card);
			}

			for (int value = 1; value < 2; value++) {
				Card card = Card.newCard(value, Card.Suit.SPADES);
				ASCENDING_FOUNDATION_NO_HEARTS.playCardOnto(card);
			}

			for (int value = 1; value < 12; value++) {
				Card card = Card.newCard(value, Card.Suit.CLUBS);
				ASCENDING_FOUNDATION_NO_HEARTS.playCardOnto(card);
			}
		}

	}

	// @Ignore
	@Test
	public void testCardCanBePlayedOnto() {

		Assert.assertTrue(ASCENDING_FOUNDATION.cardCanBePlayedOnto(Card
				.newCard(2, Suit.SPADES)));
		Assert.assertTrue(ASCENDING_FOUNDATION.cardCanBePlayedOnto(Card
				.newCard(5, Suit.DIAMONDS)));
		Assert.assertTrue(ASCENDING_FOUNDATION.cardCanBePlayedOnto(Card
				.newCard(Card.QUEEN, Suit.CLUBS)));
		Assert.assertTrue(ASCENDING_FOUNDATION.cardCanBePlayedOnto(Card
				.newCard(7, Suit.HEARTS)));

		Assert.assertFalse(ASCENDING_FOUNDATION_NO_HEARTS
				.cardCanBePlayedOnto(Card.newCard(8, Suit.HEARTS)));
		Assert.assertFalse(ASCENDING_FOUNDATION_NO_HEARTS
				.cardCanBePlayedOnto(Card.newCard(8, Suit.SPADES)));
		Assert.assertFalse(ASCENDING_FOUNDATION_NO_HEARTS
				.cardCanBePlayedOnto(Card.newCard(8, Suit.CLUBS)));
		Assert.assertFalse(ASCENDING_FOUNDATION_NO_HEARTS
				.cardCanBePlayedOnto(Card.newCard(8, Suit.DIAMONDS)));

	}

	@Test
	public void testGetTopCards() {

		{
			// // HEARTS, DIAMONDS, SPADES, CLUBS
			// for (int value = 1; value < 7; value++) {
			// Card card = Card.newCard(value, Card.Suit.HEARTS);
			// for (int value = 1; value < 5; value++) {
			// Card card = Card.newCard(value, Card.Suit.DIAMONDS);
			// for (int value = 1; value < 2; value++) {
			// Card card = Card.newCard(value, Card.Suit.SPADES);
			// for (int value = 1; value < 12; value++) {
			// Card card = Card.newCard(value, Card.Suit.CLUBS);

			// Expected top cards
			List<Card> expectedTopCards = Arrays.asList(
					Card.newCard(6, Card.Suit.HEARTS),
					Card.newCard(4, Card.Suit.DIAMONDS),
					Card.newCard(1, Card.Suit.SPADES),
					Card.newCard(Card.JACK, Card.Suit.CLUBS));
			List<Card> expectedTopCardsMatch = new ArrayList<Card>();

			// Get the top cards
			List<Card> topCards = ASCENDING_FOUNDATION.getTopCards();

			// Iterate through the top cards and make sure it's one of the
			// expected
			for (Card topCard : topCards) {

				int index = 0;
				while (index < expectedTopCards.size()) {

					// Match
					if (expectedTopCards.get(index).getValue() == topCard
							.getValue()) {
						expectedTopCards.remove(expectedTopCards.get(index));
						break;
					}
				}
			}
			Assert.assertTrue(expectedTopCards.isEmpty());

		}

	}

	@Ignore
	@Test
	public void testPrettyPrint() {

		// Test basics (no nulls)
		{
			System.out.println("  ---------------  FOUNDATION --------------");
			System.out.println(ASCENDING_FOUNDATION.toString());

		}

		// Test with nulls
		{

			System.out
					.println("  ---------------  FOUNDATION (NO HEARTS) --------------");
			System.out.println(ASCENDING_FOUNDATION_NO_HEARTS.toString());

		}
	}

}
