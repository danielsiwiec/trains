package com.danielsiwiec.trains.input;

import java.util.ArrayList;
import java.util.List;

import com.danielsiwiec.trains.GraphController;

public class QuerySuiteImpl implements QuerySuite {

	public List<Integer> runQueries(GraphController controller) {
		List<Integer> results = new ArrayList<Integer>();
		results.add(controller.getDistance('A', 'B', 'C'));
		results.add(controller.getDistance('A', 'D'));
		results.add(controller.getDistance('A', 'D', 'C'));
		results.add(controller.getDistance('A', 'E', 'B', 'C', 'D'));
		results.add(controller.getDistance('A','E','D'));
		results.add(controller.findRoundTripsByMaximumAmountOfStops('C', 3));
		results.add(controller.findTripsByExactAmountOfStop('A', 'C', 4));
		results.add(controller.findShortestTrip('A', 'C'));
		results.add(controller.findShortestRoundTrip('B'));
		results.add(controller.findRoundTripsByMaximumDistance('C', 30));
		return results;
	}

}
