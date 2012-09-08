package com.danielsiwiec.trains;

import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.danielsiwiec.trains.model.Graph;
import com.danielsiwiec.trains.pathfinders.ByDistanceFinder;
import com.danielsiwiec.trains.pathfinders.ByStopsFinder;
import com.danielsiwiec.trains.pathfinders.ShortestPathFinder;
import com.danielsiwiec.trains.util.TestGraphProvider;

@Test
public class GraphControllerImplTest {
	
	GraphController graphController;
	ByDistanceFinder byDistanceFinder;
	ByStopsFinder byStopsFinder;
	ShortestPathFinder pathFinder;
	Graph graph;
	
	@BeforeClass
	public void init(){
		graph = TestGraphProvider.createGraph();
	}
	
	// reset mocks
	@BeforeMethod
	public void initMethod(){
		byDistanceFinder = createByDistanceFinderMock();
		byStopsFinder = createByStopsFinderMock();
		pathFinder = createPathFinder();
		graphController = new GraphControllerImpl(graph,byStopsFinder,byDistanceFinder,pathFinder);
	}

	private ShortestPathFinder createPathFinder() {
		return mock(ShortestPathFinder.class);
	}

	private ByStopsFinder createByStopsFinderMock() {
		return mock(ByStopsFinder.class);
	}

	private ByDistanceFinder createByDistanceFinderMock() {
		return mock(ByDistanceFinder.class);
	}

	public void canGetDistanceForTwoStations(){
		int distance = graphController.getDistance('A','B');
		assertEquals(distance,1);
	}
	
	public void canGetDistanceForMultipleStations(){
		int distance = graphController.getDistance('A','B','C','D');
		assertEquals(distance,8);
	}
	
	public void returnsNullForMissingRoute(){
		assertNull(graphController.getDistance('A','B','C','Z'));
	}
	
	public void canFindRoundTripsByMaximumAmoutOfStops(){
		when(byStopsFinder.findRoundTripsByMaximumAmountOfStops(graph.getStation('A'), 4)).thenReturn(3);
		int roundTrips = graphController.findRoundTripsByMaximumAmountOfStops('A',4);
		assertEquals(roundTrips,3);
	}
	
	public void canFindTripsByExactAmountOfStops(){
		when(byStopsFinder.findTripsByExactAmountOfStop(graph.getStation('A'),graph.getStation('C'), 2)).thenReturn(2);
		int trips = graphController.findTripsByExactAmountOfStop('A','C',2);
		assertEquals(trips,2);
	}
	
	public void canFindShortesTrip(){
		when(pathFinder.findShortestTrip(graph.getStation('A'), graph.getStation('D'))).thenReturn(4);
		int length = graphController.findShortestTrip('A','D');
		assertEquals(length,4);
	}
	
	public void canFindRoundTripsByMaximumDistance(){
		when(byDistanceFinder.findRoundTripsByMaximumDistance(graph.getStation('A'), 7)).thenReturn(1);
		int roundTrips = graphController.findRoundTripsByMaximumDistance('A',7);
		assertEquals(roundTrips,1);
	}

}
