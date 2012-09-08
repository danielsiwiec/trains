package com.danielsiwiec.trains.model;

import org.testng.annotations.Test;

import com.danielsiwiec.trains.MissingRouteException;
import com.danielsiwiec.trains.model.Graph;

import static org.testng.Assert.*;

@Test
public class GraphTest {

	public void canBuildGraph() {
		@SuppressWarnings("unused")
		Graph graph = new Graph.StringGraphBuilder().build();
	}

	public void canAddRoute() {
		Graph graph = new Graph.StringGraphBuilder().withRoute("AB5").build();
		assertEquals(graph.getStation('A').getDistanceTo('B'), 5);
	}

	public void addingSameRouteTwiceOverrides() {
		Graph graph = new Graph.StringGraphBuilder().withRoute("AB5").withRoute("AB10").build();
		assertEquals(graph.getStation('A').getDistanceTo('B'), 10);
	}

	public void canAddTwoRoutesFromOneStation() {
		Graph graph = new Graph.StringGraphBuilder().withRoute("AB5").withRoute("AC6").build();
		assertEquals(graph.getStation('A').getDistanceTo('B'), 5);
		assertEquals(graph.getStation('A').getDistanceTo('C'), 6);
	}

	public void canAddTwoRoutesToOneStation() {
		Graph graph = new Graph.StringGraphBuilder().withRoute("AC5").withRoute("BC6").build();
		assertEquals(graph.getStation('A').getDistanceTo('C'), 5);
		assertEquals(graph.getStation('B').getDistanceTo('C'), 6);
	}

	public void canAddRouteBothWays() {
		Graph graph = new Graph.StringGraphBuilder().withRoute("AB1").withRoute("BA2").build();
		assertEquals(graph.getStation('A').getDistanceTo('B'), 1);
		assertEquals(graph.getStation('B').getDistanceTo('A'), 2);
	}

	@Test(expectedExceptions = MissingRouteException.class, expectedExceptionsMessageRegExp = "Missing connection: A-C")
	public void nonExistingFromStationThrowsException() {
		Graph graph = new Graph.StringGraphBuilder().withRoute("AB5").build();
		graph.getStation('A').getDistanceTo('C');
	}

	@Test(expectedExceptions = MissingRouteException.class, expectedExceptionsMessageRegExp = "Missing station: C")
	public void nonExistingToStationThrowsException() {
		Graph graph = new Graph.StringGraphBuilder().withRoute("AB5").build();
		graph.getStation('C').getDistanceTo('B');
	}
}
