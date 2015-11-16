package com.keppil.caesarsolver;

import static org.junit.Assert.*;

import org.junit.Test;

public class LanguageTest {
	
	@Test
	public void languagesHaveTheRightAmountOfCharacters() throws Exception {
		assertEquals(29, Language.SWEDISH.getAlphabet().getCharacters().size());
		assertEquals(26, Language.ENGLISH.getAlphabet().getCharacters().size());
	}
}
