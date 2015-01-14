package com.scottdavidson.cards.util;

/**
 * Contains a card from the top of a Tableau along with its Orientation (Neutral, ASC, DESC)
 * 
 * @author scdavidson
 *
 */
public class ColoradoTableauCard {
	private final Card card;
	private final Orientation orientation;
	
	public static ColoradoTableauCard newColoradoTableauCard(Card card, Orientation orientation){
		return new ColoradoTableauCard( card,  orientation);
	}	
	
	
	public Card getCard() {
		return card;
	}




	public Orientation getOrientation() {
		return orientation;
	}




	private ColoradoTableauCard(Card card, Orientation orientation){
		this.card = card;
		this.orientation = orientation;
	}

}
