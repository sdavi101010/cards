package com.scottdavidson.cards.util;

import org.junit.Assert;
import org.junit.Test;

public class PlayScoreTest {

	@Test
	public void testCannotPlay() {
		
		PlayScore cannotPlay = PlayScore.newCannotPlayScore();
		Assert.assertEquals(0, cannotPlay.getScore());
	}

	@Test
	public void testDefaultPlay() {
		
		PlayScore defaultPlay = PlayScore.newDefaultPlayScore();
		Assert.assertEquals(0, defaultPlay.getScore());
	}

	@Test
	public void testPlay() {
		
		PlayScore score55 = PlayScore.newPlayScore(55, "Score 55");
		Assert.assertEquals(55, score55.getScore());
		Assert.assertEquals("Score 55", score55.getBasis());
	}

}
