package com.danielsiwiec.trains.pathfinders;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.danielsiwiec.trains.MissingRouteException;
import com.danielsiwiec.trains.model.Graph;
import com.danielsiwiec.trains.util.TestGraphProvider;

@Test
public class DijkstraShortesPathFinderTest {
	
	private ShortestPathFinder pathFinder;
	private Graph graph = TestGraphProvider.createGraph();
	
	@BeforeClass
	public void init(){
		pathFinder = new DijkstraShortesPathFinder(graph.getAllStations());
	}
	
	public void findsShortestPath(){
		int trip = pathFinder.findShortestTrip(graph.getStation('A'), graph.getStation('D'));
		assertEquals(trip,5);
	}
	
	public void findsShortestRoundPath(){
		int trip = pathFinder.findShortestRoundTrip(graph.getStation('A'));
		assertEquals(trip,3);
	}
	
	@Test(expectedExceptions=MissingRouteException.class)
	public void throwsExceptionWhenNoRoute(){
		pathFinder.findShortestTrip(graph.getStation('A'), graph.getStation('F'));
	}

}