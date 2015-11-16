package com.keppil.caesarsolver;

import java.util.List;

/**
 * Holds a text in the right format for cryptographic operations.
 *
 */
public class CryptoText {
	private final String text;
	private final List<Character> characters;

	/**
	 * Creates a new {@link CryptoText} from the provided text string, filtered
	 * according to the language.
	 * 
	 * @param rawText
	 *            The raw text to start from.
	 * @param language
	 *            The language to use to filter the raw text.
	 */
	public CryptoText(String rawText, Language language) {
		String upperCaseText = rawText.toUpperCase();
		characters = language.getAlphabet().getCharacters();
		StringBuilder sb = new StringBuilder();
		upperCaseText.chars().mapToObj(i -> (char) i).filter(characters::contains).forEachOrdered(sb::append);
		this.text = sb.toString();
	}

	/**
	 * Private constructor to be used when shifting the characters of a text.
	 * 
	 */
	private CryptoText(String text, List<Character> characters) {
		this.text = text;
		this.characters = characters;
	}

	public String getText() {
		return text;
	}

	/**
	 * Shifts each character of this {@link CryptoText} the specified number of
	 * steps.
	 * 
	 * @param steps
	 *            How far to shift. Must be a positive number.
	 * @return A new {@link CryptoText} containing the shifted text.
	 */
	public CryptoText shiftBy(int steps) {
		if (steps <= 0) {
			throw new IllegalArgumentException("'steps' must be > 0!");
		}
		StringBuilder sb = new StringBuilder();
		text.chars().mapToObj(i -> (char) i)
				.map(c -> characters.get((characters.indexOf(c) + steps) % characters.size()))
				.forEachOrdered(sb::append);
		return new CryptoText(sb.toString(), characters);
	}

}
