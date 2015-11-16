package com.keppil.caesarsolver;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class CryptoTextTest {

	@Test
	public void constructorFiltersAllNonAlphabetCharacters() throws Exception {
		String test = "ABC43274 'FOO";
		assertEquals("ABCFOO", new CryptoText(test, Language.ENGLISH.getAlphabet()).getText());
	}
	
	@Test
	public void constructorConvertsCharactersToUpperCase() throws Exception {
		String test = "bar";
		assertEquals("BAR", new CryptoText(test, Language.ENGLISH.getAlphabet()).getText());
	}
	
	@Test
	public void shiftByStartsOverWhenReachingEndOfAlphabet() throws Exception {
		String test = "ABÅÄÖ";
		CryptoText original = new CryptoText(test, Language.SWEDISH.getAlphabet());
		assertEquals("CDÖAB", original.shiftBy(2).getText());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shiftByThrowsIllegalArgumentExceptionForNegativeSteps() throws Exception {
		new CryptoText("foo", Language.SWEDISH.getAlphabet()).shiftBy(-1);
	}
	
	@Test
	public void shiftByReturnsTheSameInstanceIfStepsIsZero() throws Exception {
		CryptoText original = new CryptoText("foo", Language.SWEDISH.getAlphabet());
		assertEquals(original, original.shiftBy(0));
	}
	
	@Test
	public void getDistributionReturnsTheCorrectDistribution() throws Exception {
		String text = "abuACac";
		Map<Character, Long> expected = new HashMap<>();
		expected.put('A', 3L);
		expected.put('B', 1L);
		expected.put('U', 1L);
		expected.put('C', 2L);
		assertEquals(expected, new CryptoText(text, Language.ENGLISH.getAlphabet()).getDistribution());
	}
}
