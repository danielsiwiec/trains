package com.danielsiwiec.trains.pathfinders;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.danielsiwiec.trains.MissingRouteException;
import com.danielsiwiec.trains.model.Graph;
import com.danielsiwiec.trains.model.Station;
import com.danielsiwiec.trains.util.TestGraphProvider;

@Test
public class DijkstraShortesPathFinderTest {
	
	private ShortestPathFinder pathFinder;
	private Graph graph = TestGraphProvider.createGraph();
	
	@BeforeClass
	public void init(){
		pathFinder = new DijkstraShortesPathFinder(graph.getAllStations());
	}
	
	public void findsShortesPath(){
		int trip = pathFinder.findShortestTrip(graph.getStation('A'), graph.getStation('D'));
		assertEquals(trip,5);
	}
	
	@Test(expectedExceptions=MissingRouteException.class)
	public void throwsExceptionWhenNoRoute(){
		pathFinder.findShortestTrip(graph.getStation('A'), new Station('Z'));
	}

}