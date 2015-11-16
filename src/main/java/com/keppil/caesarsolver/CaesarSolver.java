package com.keppil.caesarsolver;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.logging.Logger;

public class CaesarSolver {

	private static final Logger log = Logger.getLogger(CaesarSolver.class.getName());

	private CryptoText encryptedText;

	public CaesarSolver(CryptoText encryptedText) {
		this.encryptedText = encryptedText;
	}

	public static void main(String[] args) throws Exception {
		solvePuzzle("texts/hellman.txt", Language.SWEDISH.getAlphabet());
		solvePuzzle("texts/pearljam.txt", Language.ENGLISH.getAlphabet());
	}

	private static void solvePuzzle(String fileName, Alphabet alphabet) throws Exception {
		Path filePath = Paths.get(CaesarSolver.class.getClassLoader().getResource(fileName).toURI());
		String text = Files.lines(filePath, Charset.forName("UTF-8")).reduce(String::concat).get();
		log.info(String.format("Retrieved text '%s'...", fileName));
		CryptoText cryptoText = new CryptoText(text, alphabet).shiftBy(new Random().nextInt(alphabet.getCharacters().size()));
		log.info("Shifted text by an random amount...");
		CaesarSolver caesarSolver = new CaesarSolver(cryptoText);
		log.info("Created solver...");
		int shifts = caesarSolver.decrypt();
		log.info(String.format("Proposed solution: %d shifts...", shifts));
		CryptoText solution = cryptoText.shiftBy(shifts);
		log.info(String.format("Here it is: %s", solution.getText()));
	}

	/**
	 * Decrypts the current text. In other words, finds the number of shifts
	 * that maximizes the probability that the text is in its original form.
	 * 
	 * @return The probable number of shifts needed to get to the original
	 *         state.
	 */
	public int decrypt() {
		Alphabet alphabet = encryptedText.getAlphabet();
		double smallestDistance = Double.MAX_VALUE;
		int solution = 0;
		for (int i = 0; i < alphabet.getCharacters().size(); i++) {
			Map<Character, Long> distribution = encryptedText.shiftBy(i).getDistribution();
			double distance = calculateDistance(distribution, alphabet.getFrequencies());
			if (distance < smallestDistance) {
				smallestDistance = distance;
				solution = i;
			}
			log.finer(String.format("Shifts: %d, Distance: %f", i, distance));
		}
		return solution;
	}

	/**
	 * Calculates the chi-squared distance between a distribution and an
	 * expected frequency map, as the .
	 * 
	 * @param distribution
	 *            The distribution.
	 * @param frequencies
	 *            The expected frequencies.
	 * @return The total chi-squared distance between the two.
	 */
	public static double calculateDistance(Map<Character, Long> distribution, Map<Character, Double> frequencies) {
		long textLength = distribution.values().stream().reduce(0L, Long::sum);
		double distance = 0;
		for (Entry<Character, Double> entry : frequencies.entrySet()) {
			double expectedCount = entry.getValue() * textLength / 100;
			long actualCount = distribution.getOrDefault(entry.getKey(), 0L);
			distance += Math.pow(expectedCount - actualCount, 2) / expectedCount;
		}
		return distance;
	}
}
