package com.danielsiwiec.trains.pathfinders;

import com.danielsiwiec.trains.model.Station;

public interface ShortestPathFinder {

	public int findShortestTrip(Station from, Station to);
}