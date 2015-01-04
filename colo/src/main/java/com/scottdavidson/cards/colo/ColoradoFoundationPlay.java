package com.scottdavidson.cards.colo;

import com.scottdavidson.cards.util.Card;
import com.scottdavidson.cards.util.CardUtilException;
import com.scottdavidson.cards.util.Foundation;
import com.scottdavidson.cards.util.Game;
import com.scottdavidson.cards.util.Play;

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
public class ColoradoFoundationPlay implements Play {

	private boolean playTableauOnAscendingFoundation = false;
	private boolean playTableauOnDescendingFoundation = false;
	private boolean playDeckCardOnAscendingFoundation = false;
	private boolean playDeckCardOnDescendingFoundation = false;
	private ColoradoGame coloradoGame;

	@Override
	public void evaluatePlay(Game game) {

		// Error check - must be a ColoradoGame object
		if (!(game instanceof ColoradoGame)) {
			throw new CardUtilException(
					"Can't evaluate game object; it is not of type ColoradoGame : "
							+ game.getClass());
		}

		// Set the coloradoGame member variable
		this.coloradoGame = (ColoradoGame) game;

		// Just check to see if a Tableau or the Deck card can be played
		//
		// Check to see if can play on ASC Foundation
		Foundation ascendingFoundation = coloradoGame.getAscendingFoundation();
		for (Card card : coloradoGame.getTableaus().getTopCards()) {
			if (ascendingFoundation.cardCanBePlayedOnto(card)) {
				playTableauOnAscendingFoundation = true;
				return;
			}
		}

		// Check to see if can play on DESC Foundation
		Foundation descendingFoundation = coloradoGame
				.getDescendingFoundation();
		for (Card card : coloradoGame.getTableaus().getTopCards()) {
			if (descendingFoundation.cardCanBePlayedOnto(card)) {
				playTableauOnDescendingFoundation = true;
				return;
			}
		}

		// Check to see if can play deck card on ASC Foundation
		Card deckCard = this.coloradoGame.getDeckCard();
		if (ascendingFoundation.cardCanBePlayedOnto(deckCard)) {
			playDeckCardOnAscendingFoundation = true;
			return;
		}

		// Check to see if can play deck card on DESC Foundation
		if (descendingFoundation.cardCanBePlayedOnto(deckCard)) {
			playDeckCardOnDescendingFoundation = true;
			return;
		}

	}

	@Override
	public boolean canPlay() {

		// Return the logical or of the play booleans
		return this.playTableauOnAscendingFoundation
				|| this.playTableauOnDescendingFoundation
				|| this.playDeckCardOnAscendingFoundation
				|| this.playDeckCardOnDescendingFoundation;
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
		this.playTableauOnDescendingFoundation = false;
		this.playDeckCardOnAscendingFoundation = false;
		this.playDeckCardOnDescendingFoundation = false;
	}

	@Override
	public int getScore() {

		if (canPlay()) {
			return 100;
		} else {
			return 0;
		}
	}

}
