package com.keppil.caesarsolver;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class AlphabetTest {
	
	@Test
	public void getCharactersReturnsAllCharactersInTheCorrectOrder() throws Exception {
		Alphabet alphabet = new Alphabet("frequency-test.txt");
		List<Character> actual= alphabet.getCharacters();
		List<Character> expected = Arrays.asList('A', 'S');
		assertEquals(expected, actual);
	}
}
