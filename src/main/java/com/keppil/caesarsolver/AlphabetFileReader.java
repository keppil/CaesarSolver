package com.keppil.caesarsolver;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AlphabetFileReader {

	public static Alphabet createAlphabetFromFile(Path alphabetFile) throws IOException {
		List<String> lines = Files.readAllLines(alphabetFile, Charset.forName("UTF-8"));
		Map<Character, Double> frequencies = new LinkedHashMap<>();
		for (String line : lines) {
			String[] tokens = line.split("\\s+");
			frequencies.put(tokens[0].charAt(0), Double.parseDouble(tokens[1]));
		}
		return new Alphabet(frequencies);
	}
}
