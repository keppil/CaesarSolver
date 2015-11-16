package com.keppil.caesarsolver;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Holds a text in the right format for cryptographic operations.
 *
 */
public class CryptoText {
	private final String text;
	private final Alphabet alphabet;

	/**
	 * Creates a new {@link CryptoText} from the provided text string, filtered
	 * according to the language.
	 * 
	 * @param rawText
	 *            The raw text to start from.
	 * @param alphabet
	 *            The alphabet to use to filter the raw text.
	 */
	public CryptoText(String rawText, Alphabet alphabet) {
		this.alphabet = alphabet;
		String upperCaseText = rawText.toUpperCase();
		List<Character> characters = alphabet.getCharacters();
		StringBuilder sb = new StringBuilder();
		upperCaseText.chars().mapToObj(i -> (char) i).filter(characters::contains).forEachOrdered(sb::append);
		this.text = sb.toString();
	}

	/**
	 * Private constructor to be used when shifting the characters of a text.
	 * 
	 */
	private CryptoText(String text, CryptoText toCopy) {
		this.text = text;
		this.alphabet = toCopy.getAlphabet();
	}

	public String getText() {
		return text;
	}

	public Alphabet getAlphabet() {
		return alphabet;
	}

	/**
	 * Shifts each character of this {@link CryptoText} the specified number of
	 * steps.
	 * 
	 * @param steps
	 *            How far to shift. Must not be a negative number.
	 * @return A new {@link CryptoText} containing the shifted text.
	 */
	public CryptoText shiftBy(int steps) {
		if (steps < 0) {
			throw new IllegalArgumentException("'steps' must be >= 0!");
		} else if (steps == 0) {
			return this;
		}
		StringBuilder sb = new StringBuilder();
		List<Character> characters = alphabet.getCharacters();
		text.chars().mapToObj(i -> (char) i)
				.map(c -> characters.get((characters.indexOf(c) + steps) % characters.size()))
				.forEachOrdered(sb::append);
		return new CryptoText(sb.toString(), this);
	}

	/**
	 * Gets the character distribution of the text.
	 * 
	 * @return A map of the characters, along with the number of occurrences.
	 */
	public Map<Character, Long> getDistribution() {
		return text.chars().mapToObj(i -> (char) i).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
	}

}
