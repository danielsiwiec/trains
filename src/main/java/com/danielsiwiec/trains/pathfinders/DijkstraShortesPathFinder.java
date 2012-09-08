package com.danielsiwiec.trains.pathfinders;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import com.danielsiwiec.trains.MissingRouteException;
import com.danielsiwiec.trains.model.Station;

public class DijkstraShortesPathFinder implements ShortestPathFinder {

	private static final int INITIAL = 10;

	private Collection<Station> stations;
	private Set<Station> visited;
	private PriorityQueue<Station> unvisited;

	public DijkstraShortesPathFinder(Collection<Station> collection) {
		this.stations = collection;
	}

	public int findShortestTrip(Station from, Station to) {
		initialize();
		unvisited.add(from);
		from.setDistanceFromSource(0);

		while (!unvisited.isEmpty()) {
			Station closest = unvisited.poll();
			if (closest.equals(to)) {
				return closest.getDistanceFromSource();
			}
			visited.add(closest);
			relax(closest);
		}
		throw new MissingRouteException("Missing connection: " + from + " - " + to);
	}
	
	private void relax(Station station) {
		for (Station neighbor : station.getDestinations()) {
			if (!visited.contains(neighbor)) {
				int currentKnownDistance = getCurrentKnownDistance(station, neighbor);
				if (neighbor.getDistanceFromSource() > currentKnownDistance) {
					neighbor.setDistanceFromSource(currentKnownDistance);
					unvisited.add(neighbor);
				}
			}
		}
	}

	public int findShortestRoundTrip(Station from) {
		initialize();
		unvisited.add(from);
		from.setDistanceFromSource(0);
		// to allow round trip, for the first iteration skip 'are we there yet'
		// check and don't add station to visited
		boolean start = true;

		while (!unvisited.isEmpty()) {
			Station closest = unvisited.poll();
			if (!start && closest.equals(from)) {
				return closest.getDistanceFromSource();
			}
			if (!start) {
				visited.add(closest);
			}
			relaxRoundTrip(closest, from);
			start = false;
		}
		throw new MissingRouteException("Missing round connection: " + from);
	}

	private void relaxRoundTrip(Station station, Station destination) {
		for (Station neighbor : station.getDestinations()) {
			if (!visited.contains(neighbor)) {
				int currentKnownDistance = getCurrentKnownDistance(station, neighbor);
				// to allow round trip if the neighbor is the destination, add
				// it back to the queue
				if (neighbor.getDistanceFromSource() > currentKnownDistance || neighbor.equals(destination)) {
					neighbor.setDistanceFromSource(currentKnownDistance);
					unvisited.add(neighbor);
				}
			}
		}
	}

	private void initialize() {
		visited = new HashSet<Station>();
		unvisited = new PriorityQueue<Station>(INITIAL, new DistanceComparator());
		initializeDistances();
	}

	private void initializeDistances() {
		for (Station station : stations) {
			station.setDistanceFromSource(Integer.MAX_VALUE);
		}
	}

	private int getCurrentKnownDistance(Station current, Station neighbor) {
		return current.getDistanceFromSource() + current.getDistanceTo(neighbor.getId());
	}

}

class DistanceComparator implements Comparator<Station> {

	public int compare(Station arg0, Station arg1) {
		if (arg0.getDistanceFromSource() > arg1.getDistanceFromSource()) {
			return 1;
		} else {
			return -1;
		}
	}

}
