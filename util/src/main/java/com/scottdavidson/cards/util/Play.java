package com.scottdavidson.cards.util;

public interface Play {

	/**
	 * Evaluates this play on the provided game and stores the results for
	 * future interrogation.
	 * 
	 * @param game
	 */
	void evaluatePlay(Game game);

	/**
	 * Returns true if this play can be made on the evaluated game; false
	 * otherwise.
	 * 
	 * @param game
	 * @return true if this play can be made on the evaluated game; false
	 *         otherwise.
	 * @throws IllegalStateException if this Play doesn't have an evaluated Game.
	 */
	boolean canPlay();

	/**
	 * Returns the evaluated score of this play on the evaluated game. <br>
	 * <p>
	 * By having a separate score accessor method, this Play can apply a more
	 * efficient implementation by first just determining if play is an option
	 * and then later evaluating the score (for those cases where other plays 
	 * may be used if they can be played - option versus heuristic)
	 * 
	 * @return the evaluated score of this play on the evaluated game.
	 * @throws IllegalStateException if this Play doesn't have an evaluated Game.
	 */
	int getScore();
	
	/**
	 * Execute this play on the evaluated game. 
	 * 
	 * @return the updated Game after this play.
	 */
	Game play();

	/**
	 * Resets this Play and makes it ready to evaluate a new Game.
	 */
	void resetPlay();
}
