package com.danielsiwiec.trains.pathfinders;

import com.danielsiwiec.trains.model.Station;

public interface ByStopsFinder {
	
	public int findRoundTripsByMaximumAmountOfStops(Station station, int maxStops);
	public int findTripsByExactAmountOfStop(Station from, Station to, int stops);	

}
