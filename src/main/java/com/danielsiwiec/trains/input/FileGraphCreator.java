package com.danielsiwiec.trains.input;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import com.danielsiwiec.trains.model.Graph;
import com.danielsiwiec.trains.model.Graph.StringGraphBuilder;

public class FileGraphCreator implements GraphCreator {
	
	private static final String DELIMITER = ",";
	private static final Pattern GRAPH_PATTERN = Pattern.compile("[A-Z]{2}\\d*(\\s*,\\s*[A-Z]{2}\\d*)*,?");

	public Graph createGraph(String fileName) {
		Path path = Paths.get(fileName);
		try {
			List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			StringGraphBuilder graphBuilder = new Graph.StringGraphBuilder();
			for (String line : lines){
				parseLine(graphBuilder,line);
			}
			return graphBuilder.build();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void parseLine(StringGraphBuilder graphBuilder, String line) {
		if (!GRAPH_PATTERN.matcher(line).matches()){
			throw new RuntimeException("Wrong graph format: " + line);
		}
		StringTokenizer tokenizer = new StringTokenizer(line, DELIMITER);
		while (tokenizer.hasMoreTokens()){
			graphBuilder.withRoute(tokenizer.nextToken().trim());
		}
	}

}
