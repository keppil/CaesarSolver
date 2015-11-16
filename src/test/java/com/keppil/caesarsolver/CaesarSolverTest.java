package com.keppil.caesarsolver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CaesarSolverTest {

	@Test
	public void decryptFindsCorrectBibiSolution() throws Exception {
		Alphabet biAlphabet = new Alphabet("frequency-bi.txt");
		CryptoText cryptoText = new CryptoText("biii biii ibii", biAlphabet).shiftBy(1);
		assertEquals(1, new CaesarSolver(cryptoText).decrypt());
	}
	
	@Test
	public void calculateDistanceReturnsZeroForPerfectDistribution() throws Exception {
		Alphabet biAlphabet = new Alphabet("frequency-bi.txt");
		CryptoText cryptoText = new CryptoText("biii biii ibii", biAlphabet);
		assertEquals(0, CaesarSolver.calculateDistance(cryptoText.getDistribution(), biAlphabet.getFrequencies()), 0);
	}
}
