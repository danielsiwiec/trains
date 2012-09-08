package com.danielsiwiec.trains.pathfinders;

import com.danielsiwiec.trains.model.Station;

public class RecursiveByDistanceFinder implements ByDistanceFinder {
	
	public int findRoundTripsByMaximumDistance(Station station, int maxDistance) {
		int count = 0;
		// do the first iteration up front to avoid x = x comparison
		for (Station destination : station.getDestinations()) {
			count += reccursionSearchByMaxDistance(destination, station, maxDistance - station.getDistanceTo(destination.getId()));
		}
		return count;
	}
	
	private int reccursionSearchByMaxDistance(Station current, Station to, int distanceLeft) {
		int count = 0;
		if (current == to && distanceLeft > 0) {
			count++;
		}
		if (distanceLeft > 0) {
			for (Station destination : current.getDestinations()) {
				count += reccursionSearchByMaxDistance(destination, to, distanceLeft - current.getDistanceTo(destination.getId()));
			}
		}
		return count;
	}

}
