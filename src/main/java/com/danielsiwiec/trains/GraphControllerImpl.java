package com.danielsiwiec.trains;

import java.util.Arrays;

import com.danielsiwiec.trains.model.Graph;
import com.danielsiwiec.trains.model.Route;
import com.danielsiwiec.trains.model.Station;
import com.danielsiwiec.trains.pathfinders.ByDistanceFinder;
import com.danielsiwiec.trains.pathfinders.ByStopsFinder;
import com.danielsiwiec.trains.pathfinders.ShortestPathFinder;

public class GraphControllerImpl implements GraphController {

	private Graph graph;
	private ShortestPathFinder pathFinder;
	private ByStopsFinder byStopsFinder;
	private ByDistanceFinder byDistanceFinder;

	public GraphControllerImpl(Graph graph, ByStopsFinder byStopsFinder, ByDistanceFinder byDistanceFinder, ShortestPathFinder pathFinder) {
		this.graph = graph;
		this.pathFinder = pathFinder;
		this.byStopsFinder = byStopsFinder;
		this.byDistanceFinder = byDistanceFinder;
	}

	public Integer getDistance(char... stops) {
		try{
		int distance = 0;
		Station currentStop = graph.getStation(stops[0]);
		Route currentRoute = null;

		// starting from the second station
		for (char stop : Arrays.copyOfRange(stops, 1, stops.length)) {
			currentRoute = currentStop.getRouteTo(stop);
			distance += currentRoute.getDistance();
			currentStop = currentRoute.getDestination();
		}

		return distance;
		}
		catch(MissingRouteException e){
			return null;
		}
	}

	public int findRoundTripsByMaximumAmountOfStops(char stationId, int maxStops) {
		Station station = graph.getStation(stationId);
		return byStopsFinder.findRoundTripsByMaximumAmountOfStops(station, maxStops);
	}

	public int findTripsByExactAmountOfStop(char fromId, char toId, int stops) {
		Station from = graph.getStation(fromId);
		Station to = graph.getStation(toId);
		return byStopsFinder.findTripsByExactAmountOfStop(from, to, stops);
	}

	public int findRoundTripsByMaximumDistance(char stationId, int maxDistance) {
		Station station = graph.getStation(stationId);
		return byDistanceFinder.findRoundTripsByMaximumDistance(station, maxDistance);
	}
	
	public int findShortestTrip(char fromId, char toId) {
		Station from = graph.getStation(fromId);
		Station to = graph.getStation(toId);
		return pathFinder.findShortestTrip(from, to);
	}
	
	public int findShortestRoundTrip(char stationId) {
		Station station = graph.getStation(stationId);
		return pathFinder.findShortestRoundTrip(station);
	}
}
