package com.danielsiwiec.trains.pathfinders;

import com.danielsiwiec.trains.model.Station;

public interface ByDistanceFinder {

	public int findRoundTripsByMaximumDistance(Station station, int maxDistance);

}
