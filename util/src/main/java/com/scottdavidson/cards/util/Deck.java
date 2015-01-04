package com.scottdavidson.cards.util;

import com.scottdavidson.cards.util.Card.Suit;

public class Deck {

	private final Card[] cards;
	private int usedCardsIndex = 0;

	public static Deck newShuffledDeck() {
		return new Deck(false, 1);
	}

	public static Deck newShuffledDeckWithJokers() {
		return new Deck(true, 1);
	}

	public static Deck newShuffledDoubleDeck() {
		return new Deck(false, 2);
	}

	public static Deck newShuffledDoubleDeckWithJokers() {
		return new Deck(true, 2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.scottdavidson.cards.util.IDeck#shuffle()
	 */

	public void shuffle() {

		for (int i = cards.length - 1; i > 0; i--) {
			int rand = (int) (Math.random() * (i + 1));
			Card temp = cards[i];
			cards[i] = cards[rand];
			cards[rand] = temp;
		}

	}

	/**
	 * Deal a card from the top of the deck (that is, remove it).
	 * 
	 * @return the top card from the deck.
	 */
	public Card dealCard() {

		// Error check
		if (this.usedCardsIndex >= this.cards.length) {
			throw new IllegalStateException("No more cards left to deal");
		}

		Card returnCard = cards[this.usedCardsIndex];
		this.usedCardsIndex += 1;

		return returnCard;
	}

	public String show() {
		StringBuilder displayDeck = new StringBuilder();

		int lineCount = 0;
		for (Card card : this.cards) {
			displayDeck.append(card.toString()).append(" ");

			if (lineCount == 12) {
				displayDeck.append("\n");
				lineCount = 0;
			} else {
				lineCount += 1;
			}
		}

		return displayDeck.toString();
	}

	public String conciseShow() {
		StringBuilder displayDeck = new StringBuilder();

		int lineCount = 0;
		for (Card card : this.cards) {
			displayDeck.append(card.conciseToString()).append(" ");

			if (lineCount == 12) {
				displayDeck.append("\n");
				lineCount = 0;
			} else {
				lineCount += 1;
			}
		}

		return displayDeck.toString();
	}

	private Deck() {
		this(false, 1);
	}

	private Deck(boolean withJokers, int numberOfDecks) {

		// Determine how many total cards (based on whether using jokers and
		// number of decks
		int totalCardsInASingleDeck;
		if (withJokers) {
			totalCardsInASingleDeck = 54;
		} else {
			totalCardsInASingleDeck = 52;
		}
		int totalNumberOfCards = totalCardsInASingleDeck * numberOfDecks;

		// Instantiate the deck
		this.cards = new Card[totalNumberOfCards];

		// Insert the cards sequentially (b/c it's easy and
		// we'll rely on the shuffle operation to shuffle them :-)
		for (int deck = 0; deck < numberOfDecks; deck++) {
			for (Suit suit : Suit.values()) {

				for (int cardValue = Card.ACE; cardValue <= Card.KING; cardValue++) {
					int index = (deck * totalCardsInASingleDeck) + (suit.ordinal() * 13)
							+ cardValue - 1;
					this.cards[index] = Card.newCard(cardValue, suit);
				}
			}
		}

		// If there are jokers, fill in the gaps left for them
		if (withJokers) {
			for (int deck = 0; deck < numberOfDecks; deck++) {
				int index = (deck + 1) * totalCardsInASingleDeck - 2;
				this.cards[index] = Card.newJoker();
				this.cards[index+1] = Card.newJoker();
			}
		}
	}

}
