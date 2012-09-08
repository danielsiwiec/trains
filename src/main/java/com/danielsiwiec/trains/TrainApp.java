package com.danielsiwiec.trains;

import java.util.List;

import com.danielsiwiec.trains.input.FileGraphCreator;
import com.danielsiwiec.trains.input.GraphCreator;
import com.danielsiwiec.trains.input.QuerySuite;
import com.danielsiwiec.trains.input.QuerySuiteImpl;
import com.danielsiwiec.trains.model.Graph;
import com.danielsiwiec.trains.pathfinders.ByDistanceFinder;
import com.danielsiwiec.trains.pathfinders.ByStopsFinder;
import com.danielsiwiec.trains.pathfinders.DijkstraShortestPathFinder;
import com.danielsiwiec.trains.pathfinders.RecursiveByDistanceFinder;
import com.danielsiwiec.trains.pathfinders.RecursiveByStopsFinder;
import com.danielsiwiec.trains.pathfinders.ShortestPathFinder;

public class TrainApp {

	private static final String DEFAULT_GRAPH_FILE = "src/main/resources/graph.txt";

	public static void main(String [] args) {
		String graphFilePath;
		if (args.length == 0){
			System.out.println("Using default graph file location: src/main/resources/graph.txt");
			graphFilePath = DEFAULT_GRAPH_FILE;
		}
		else {
			graphFilePath = args[0];
		}
		GraphCreator creator = new FileGraphCreator();
		Graph graph = creator.createGraph(graphFilePath);
		ByDistanceFinder byDistanceFinder = new RecursiveByDistanceFinder();
		ByStopsFinder byStopsFinder = new RecursiveByStopsFinder();
		ShortestPathFinder pathFinder = new DijkstraShortestPathFinder(graph.getAllStations());
		GraphController controller = new GraphControllerImpl(graph,byStopsFinder,byDistanceFinder,pathFinder);
		QuerySuite querySet = new QuerySuiteImpl();
		List<Integer> results = querySet.runQueries(controller);
		OutputCreator processor = new OutputCreator();
		String output = processor.createOutput(results);
		System.out.println(output);
	}

}
