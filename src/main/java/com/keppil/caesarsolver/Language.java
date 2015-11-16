package com.keppil.caesarsolver;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * The different supported languages. For each language, a frequency file is expected to be found on the path.
 *
 */
public enum Language {

	SWEDISH("se"), ENGLISH("en");

	private static final String FREQUENCY_FILE_TEMPLATE = "frequency-%s.txt";
	private Alphabet alphabet;

	private Language(String suffix) {
		String fileName = String.format(FREQUENCY_FILE_TEMPLATE, suffix);
		try {
			this.alphabet = new Alphabet(fileName);
		} catch (IOException | URISyntaxException e) {
			System.out.println("Unable to create language: " + e);
			System.out.format("The file %s might be missing.%n", fileName);
		}
	}

	/**
	 * Gets the {@link Alphabet} corresponding to this language.
	 * @return The alphabet.
	 */
	public Alphabet getAlphabet() {
		return alphabet;
	}
}
