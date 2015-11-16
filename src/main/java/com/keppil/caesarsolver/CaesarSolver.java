package com.keppil.caesarsolver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CaesarSolver {

	public static void main(String[] args) throws IOException, URISyntaxException {
		Path path = Paths.get(CaesarSolver.class.getClassLoader().getResource("hellman.txt").toURI());
		CharacterCounter.countFrequencies(Files.readAllLines(path));
	}
}
