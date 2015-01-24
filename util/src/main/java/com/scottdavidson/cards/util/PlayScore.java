package com.scottdavidson.cards.util;

public class PlayScore implements Comparable<PlayScore>{
	
	private final int score;
	private final String basis;
	
	public static PlayScore newCannotPlayScore() {
		return new PlayScore(0, "Cannot play");
	}
	public static PlayScore newDefaultPlayScore() {
		return new PlayScore(0, "Default Score");
	}
	public static PlayScore newPlayScore(int score, String basis) {
		return new PlayScore(score, basis);
	}
		
	public int getScore() {
		return score;
	}


	public String getBasis() {
		return basis;
	}


	private PlayScore(int score, String basis){
		this.score = score;
		this.basis = basis;
	}
	
	@Override
	public int compareTo(PlayScore comparisonPlayScore) {
		return Integer.valueOf(this.score).compareTo(comparisonPlayScore.getScore());
	}

}
