package com.keppil.caesarsolver;

import static org.junit.Assert.*;

import org.junit.Test;

public class CryptoTextTest {

	@Test
	public void constructorFiltersAllNonAlphabetCharacters() throws Exception {
		String test = "ABC43274 'FOO";
		assertEquals("ABCFOO", new CryptoText(test, Language.ENGLISH).getText());
	}
	
	@Test
	public void constructorConvertsCharactersToUpperCase() throws Exception {
		String test = "bar";
		assertEquals("BAR", new CryptoText(test, Language.ENGLISH).getText());
	}
	
	@Test
	public void shiftByStartsOverWhenReachingEndOfAlphabet() throws Exception {
		String test = "ABÅÄÖ";
		CryptoText original = new CryptoText(test, Language.SWEDISH);
		assertEquals("CDÖAB", original.shiftBy(2).getText());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shiftByThrowsIllegalArgumentExceptionForNegativeSteps() throws Exception {
		new CryptoText("foo", Language.SWEDISH).shiftBy(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shiftByThrowsIllegalArgumentExceptionForZeroSteps() throws Exception {
		new CryptoText("foo", Language.SWEDISH).shiftBy(0);
	}
}
