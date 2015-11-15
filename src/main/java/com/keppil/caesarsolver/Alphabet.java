package com.keppil.caesarsolver;

import java.util.Map;

/**
 * Holds a representation of an alphabet, including frequencies.
 * 
 * @author mattias
 *
 */
public class Alphabet {

	private final Map<Character, Double> frequencies;

	public Alphabet(Map<Character, Double> frequencies) {
		this.frequencies = frequencies;
	}

	public Map<Character, Double> getFrequencies() {
		return frequencies;
	}

}