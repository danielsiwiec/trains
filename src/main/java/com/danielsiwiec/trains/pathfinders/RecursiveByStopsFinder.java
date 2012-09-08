package com.danielsiwiec.trains.pathfinders;

import com.danielsiwiec.trains.model.Station;

public class RecursiveByStopsFinder implements ByStopsFinder{
	
	public int findRoundTripsByMaximumAmountOfStops(Station station, int maxStops) {
		int count = 0;
		// do the first iteration up front to avoid x = x comparison
		for (Station destination : station.getDestinations()) {
			count += reccursionSearchByStops(destination, station, maxStops - 1, false);
		}
		return count;
	}

	public int findTripsByExactAmountOfStop(Station from, Station to, int stops) {
		return reccursionSearchByStops(from, to, stops, true);
	}
	
	// 'exact' determines if we're considering only exact amount of stops
		private int reccursionSearchByStops(Station current, Station to, int stopsToGo, boolean exact) {
			int count = 0;
			if ((!exact || stopsToGo == 0) && current == to) {
				count++;
			}
			if (stopsToGo > 0) {
				for (Station destination : current.getDestinations()) {
					count += reccursionSearchByStops(destination, to, stopsToGo - 1, exact);
				}
			}
			return count;
		}

}
