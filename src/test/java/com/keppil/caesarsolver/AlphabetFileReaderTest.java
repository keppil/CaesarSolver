package com.keppil.caesarsolver;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class AlphabetFileReaderTest {
	
	@Test
	public void testName() throws Exception {
		Path alphabetFile = Paths.get(this.getClass().getClassLoader().getResource("frequency-test.txt").toURI());
		Alphabet alphabet = AlphabetFileReader.createAlphabetFromFile(alphabetFile);
		assertEquals(2, alphabet.getFrequencies().size());
	}
}
