package com.danielsiwiec.trains;

public interface GraphController {

	public Integer getDistance(char... stops);

	public int findRoundTripsByMaximumAmountOfStops(char stationId, int maxStops);

	public int findTripsByExactAmountOfStop(char fromId, char toId, int stops);

	public int findRoundTripsByMaximumDistance(char stationId, int maxDistance);

	public int findShortestTrip(char fromId, char toId);

	public int findShortestRoundTrip(char stationId);

}