package com.scottdavidson.cards.util;

import java.util.List;

/**
 * Specifies the methods required for any StackStrategy object.
 * 
 * @author scdavidson
 *
 */
public interface StackStrategy {

	/**
	 * Return <tt>true</tt> if the candidate card can be played on top of the
	 * provided top card; <tt>false</tt> otherwise.
	 * 
	 * @param topCard
	 *            the top card on a stack
	 * @param candidateCard
	 *            a candiate card to be played onto the stack
	 * @return <tt>true</tt> if the candidate card can be played on top of the
	 *         provided top card; <tt>false</tt> otherwise
	 */
	public boolean cardCanBePushed(Card topCard, Card candidateCard);

	/**
	 * Returns a list of Cards which can be played on top of the provided top
	 * card.
	 * 
	 * @param topCard
	 *            the top card on a stack
	 * @return a list of Cards which can be played on top of the provided top
	 *         card
	 */
	public List<Card> cardsThatCanBePlayed(Card topCard);

	/**
	 * Return <tt>true</tt> if the stack is complete, that is, can't accept
	 * anymore Cards based on the provided top card; <tt>false</tt>, otherwise.
	 * 
	 * @return <tt>true</tt> if the stack is complete, that is, can't accept
	 *         anymore Cards based on the provided top card; <tt>false</tt>,
	 *         otherwise
	 */
	public boolean isComplete(Card topCard);

}
