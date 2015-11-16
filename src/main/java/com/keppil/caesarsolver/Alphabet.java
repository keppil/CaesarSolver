package com.keppil.caesarsolver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds a representation of an alphabet, including frequencies.
 * 
 */
public class Alphabet {

	private final Map<Character, Double> frequencies;

	/**
	 * Initializes a new Alphabet from a frequency file.
	 * 
	 * @param frequencyFile
	 *            The name of the frequency file.
	 * @throws IOException
	 *             If the file can't be read.
	 * @throws URISyntaxException
	 *             If a URI of the file can't be constructed.
	 */
	public Alphabet(String frequencyFile) throws IOException, URISyntaxException {
		Path filePath = Paths.get(this.getClass().getClassLoader().getResource(frequencyFile).toURI());
		List<String> lines = Files.readAllLines(filePath, Charset.forName("UTF-8"));
		Map<Character, Double> frequencies = new LinkedHashMap<>();
		for (String line : lines) {
			String[] tokens = line.split("\\s+");
			frequencies.put(tokens[0].charAt(0), Double.parseDouble(tokens[1]));
		}
		this.frequencies = Collections.unmodifiableMap(frequencies);
	}

	/**
	 * Gets an ordered list of all the characters of this alphabet.
	 * 
	 * @return A list of the characters.
	 */
	public List<Character> getCharacters() {
		return new ArrayList<>(frequencies.keySet());
	}
	
	/**
	 * Gets the frequencies of this alphabet.
	 * 
	 * @return A map from character to the frequency (in percent).
	 */
	public Map<Character, Double> getFrequencies() {
		return frequencies;
	}

}