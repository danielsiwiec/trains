package com.danielsiwiec.trains.pathfinders;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.danielsiwiec.trains.MissingRouteException;
import com.danielsiwiec.trains.model.Graph;
import com.danielsiwiec.trains.model.Station;
import com.danielsiwiec.trains.util.TestGraphProvider;

@Test
public class RecursiveByDistanceFinderTest {

	private Graph graph = TestGraphProvider.createGraph();
	private ByDistanceFinder byDistanceFinder = new RecursiveByDistanceFinder();
	
	public void canFindRoundTripsByMaximumDistance(){
		Station station = graph.getStation('A');
		int roundTrips = byDistanceFinder.findRoundTripsByMaximumDistance(station,7);
		assertEquals(roundTrips,3);
	}
	
	public void returnZeroWhenNoRoute(){
		Station station = graph.getStation('D');
		int roundTrips = byDistanceFinder.findRoundTripsByMaximumDistance(station, 1);
		assertEquals(roundTrips,0);
	}
	
	@Test(expectedExceptions=MissingRouteException.class, expectedExceptionsMessageRegExp="Missing station: Z")
	public void throwsExceptionIfNoStation(){
		Station station = graph.getStation('Z');
		int roundTrips = byDistanceFinder.findRoundTripsByMaximumDistance(station, 1);
		assertEquals(roundTrips,0);
	}
	
	@Test(expectedExceptions=NullPointerException.class)
	public void throwsExceptionIfNullStation(){
		int roundTrips = byDistanceFinder.findRoundTripsByMaximumDistance(null, 1);
		assertEquals(roundTrips,0);
	}
	
	public void returnZeroWhenZeroDistance(){
		Station station = graph.getStation('A');
		int roundTrips = byDistanceFinder.findRoundTripsByMaximumDistance(station, 0);
		assertEquals(roundTrips,0);
	}

}
