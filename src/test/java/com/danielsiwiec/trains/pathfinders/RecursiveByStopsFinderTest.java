package com.danielsiwiec.trains.pathfinders;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.danielsiwiec.trains.model.Graph;
import com.danielsiwiec.trains.model.Station;
import com.danielsiwiec.trains.util.TestGraphProvider;

@Test
public class RecursiveByStopsFinderTest {
	
	private Graph graph = TestGraphProvider.createGraph();
	private ByStopsFinder byStopsFinder = new RecursiveByStopsFinder();
	
	public void canFindRoundTripsByMaximumAmoutOfStops(){
		Station station = graph.getStation('A');
		int roundTrips = byStopsFinder.findRoundTripsByMaximumAmountOfStops(station,4);
		assertEquals(roundTrips,5);
	}
	
	public void returnsZeroWhenNoRoute(){
		Station station = graph.getStation('D');
		int roundTrips = byStopsFinder.findRoundTripsByMaximumAmountOfStops(station, 1);
		assertEquals(roundTrips,0);
	}
	
	@Test(expectedExceptions=NullPointerException.class)
	public void throwsExceptionIfNullStation(){
		int roundTrips = byStopsFinder.findRoundTripsByMaximumAmountOfStops(null, 1);
		assertEquals(roundTrips,0);
	}
	
	public void returnsZeroForMaxZeroStops(){
		Station station = graph.getStation('A');
		int roundTrips = byStopsFinder.findRoundTripsByMaximumAmountOfStops(station, 0);
		assertEquals(roundTrips,0);
	}
	
	public void canFindTripsByExactAmountOfStops(){
		Station from = graph.getStation('A');
		Station to = graph.getStation('C');
		int trips = byStopsFinder.findTripsByExactAmountOfStop(from,to,2);
		assertEquals(trips,1);
	}
	
	public void returnsZeroWhenNoSuchRoute(){
		Station from = graph.getStation('A');
		Station to = graph.getStation('C');
		int trips = byStopsFinder.findTripsByExactAmountOfStop(from,to,1);
		assertEquals(trips,0);
	}
	
	public void returnsZeroForZeroStops(){
		Station from = graph.getStation('A');
		Station to = graph.getStation('C');
		int trips = byStopsFinder.findTripsByExactAmountOfStop(from,to,0);
		assertEquals(trips,0);
	}

}
