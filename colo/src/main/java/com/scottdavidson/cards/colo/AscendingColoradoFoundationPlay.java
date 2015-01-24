package com.scottdavidson.cards.colo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.scottdavidson.cards.util.Card;
import com.scottdavidson.cards.util.CardUtilException;
import com.scottdavidson.cards.util.ColoradoTableauCard;
import com.scottdavidson.cards.util.Foundation;
import com.scottdavidson.cards.util.Game;
import com.scottdavidson.cards.util.Orientation;
import com.scottdavidson.cards.util.Play;
import com.scottdavidson.cards.util.PlayScore;
import com.scottdavidson.cards.util.Tableau;

/**
 * Represents the single play that can be made in Colorado (that is play a card
 * on the Foundation).
 * 
 * @author scdavidson
 *
 */

// TODO - convert this to an interface and create multiple plays (w/ score)

// NOTES:
//
// Probably need to move to the multiple plays right away as there are many
// scenarios:
// 1: one T (or Deck) can be played on a single F
// 2. one T (or Deck) can be played on both F's
// 3. two T (same card) can be played on both F's
// 4. two or more T (unique cards) can be played on one or both F's
//
// Strategy should be :
// -- in canPlay(...), just figure out if there's any play that can be made but
// don't spend time evaluating all cases
// -- in play(...), for the first value you encounter that can be played, check
// to see if there's a second duplicate card. And then go from there. There's
// no advantage of playing one over another (except when we get into the look
// ahead strategy later, that is, with look ahead, you want to evaluate all
// options
// b/c it may be more advantageous to play say a 3 (ASC) and not a 4 (DESC) of
// the same suit, b/c then you can play the 4 on the ASC (which may be
// advantageous
// simply b/c you're moving the Foundation that's farthest from the goal, but
// also b/c there's a 5, 6 and 7 also to be played.

// NOTES 2 (Simple):
// ** For an initial implementation (possibly rename this to SimpleAscendingCFP)
// 1. Identify *all* of the cards that can be played on the ASC foundation.
// 2. Find first card which is defined as ASC and play it (DONE)
// 3. If two cards of same instance are available, define first one as DESC and
// play the second (DONE)
// 4. Find first card available and play it (DONE)

public class AscendingColoradoFoundationPlay implements Play {

	private boolean playTableauOnAscendingFoundation = false;
	private TreeMap<PlayScore, Tableau> ascendingTableauToPlayFromMap = new TreeMap<PlayScore, Tableau>();
	private boolean playDeckCardOnAscendingFoundation = false;
	private PlayScore deckCardPlayScore = PlayScore.newCannotPlayScore();
	private ColoradoGame coloradoGame;

	@Override
	public void evaluatePlay(Game game) {

		// Error check - must be a ColoradoGame object
		if (!(game instanceof ColoradoGame)) {
			throw new CardUtilException(
					"Can't evaluate game object; it is not of type ColoradoGame : "
							+ game.getClass());
		}

		// Reset play
		resetPlay();

		// Set the coloradoGame member variable
		this.coloradoGame = (ColoradoGame) game;

		// Check to see if a Tableau card can be played
		List<Card> playableCards = this.coloradoGame.getAscendingFoundation()
				.playableCards();
		for (Card playableCard : playableCards) {

			// Ask the Tableuas for a Tableau which matches the current playable
			// card (it could be multiple)
			List<Tableau> tableauList = this.coloradoGame.getTableaus()
					.getIndexedTableaus(playableCard);

			// If there's a Tableau (or two), evaluate it
			if (null != tableauList) {

				// If there are multiple, then choose the one that's ASC
				if (tableauList.size() == 2) {

					// Check first (0th) for ASC
					if (tableauList.get(0).topCard().getOrientation() == Orientation.ASCENDING) {
						playFromThisTableau(tableauList.get(0), true);
					}

					// If not first, then use second (whether ASC or not)
					else {
						playFromThisTableau(
								tableauList.get(1),
								tableauList.get(1).topCard().getOrientation() == Orientation.ASCENDING);

					}
				}

				// Else (not multiple), just play it
				else {
					playFromThisTableau(
							tableauList.get(0),
							tableauList.get(0).topCard().getOrientation() == Orientation.ASCENDING);
				}

			}
		}

		// If a Tableau card can't be played, try from the Deck
		if (!this.playTableauOnAscendingFoundation) {

			// Get the Ascending Foundation and deck card
			Foundation ascendingFoundation = this.coloradoGame
					.getAscendingFoundation();
			Card deckCard = this.coloradoGame.getDeckCard();

			// Check deck card
			if (ascendingFoundation.cardCanBePlayedOnto(deckCard)) {
				playFromDeck();
			}

		}

	}

	/**
	 * Helper method to ensure all properties are consistently set for case
	 * where playing from a Tableau
	 * 
	 * @param tableau
	 */
	protected void playFromThisTableau(Tableau tableau, boolean ascending) {

		// Set the flags
		this.playTableauOnAscendingFoundation = true;
		this.playDeckCardOnAscendingFoundation = false;

		// Store the tableau and it's score
		//
		// 100 points for ascending
		if (ascending) {
			this.ascendingTableauToPlayFromMap.put(
					PlayScore.newPlayScore(100, "Foundation ascending play"),
					tableau);
		}

		// 99 points for non-ascending (better than playing from the deck)
		else {
			this.ascendingTableauToPlayFromMap
					.put(PlayScore.newPlayScore(99,
							"Foundation non-ascending play"), tableau);
		}

	}

	/**
	 * Helper method to ensure all properties are consistently set for case
	 * where playing from a Tableau
	 * 
	 * @param tableau
	 */
	protected void playFromDeck() {
		this.playDeckCardOnAscendingFoundation = true;
		this.playTableauOnAscendingFoundation = false;
		this.deckCardPlayScore = PlayScore.newPlayScore(98,
				"Can't play from Foundation, but can from Deck");
	}

	@Override
	public boolean canPlay() {

		// Return the logical or of the play booleans
		return this.playTableauOnAscendingFoundation
				|| this.playDeckCardOnAscendingFoundation;
	}

	@Override
	public ColoradoGame play() {

		// If play on ASC
		if (this.playTableauOnAscendingFoundation) {

		}

		return null;
	}

	@Override
	public void resetPlay() {
		this.playTableauOnAscendingFoundation = false;
		this.playDeckCardOnAscendingFoundation = false;
	}

	@Override
	public PlayScore getScore() {

		// If can play
		if (canPlay()) {

			// If deck card - return the deck card play score
			if (this.playDeckCardOnAscendingFoundation) {
				return this.deckCardPlayScore;
			}

			// Else, if play from tableau sort the map of tableaus
			else {

				// Return the last (largest) key from the map (which is the
				// largest score)
				return this.ascendingTableauToPlayFromMap.lastKey();
			}
		} else {
			return PlayScore.newCannotPlayScore();
		}
	}

}
