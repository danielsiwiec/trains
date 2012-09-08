package com.danielsiwiec.trains.input;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.danielsiwiec.trains.input.FileGraphCreator;
import com.danielsiwiec.trains.input.GraphCreator;
import com.danielsiwiec.trains.model.Graph;
import com.danielsiwiec.trains.model.Station;

@Test
public class FileGraphCreatorTest {
	
	private GraphCreator fileGraphCreator = new FileGraphCreator();
	
	public void canCreateGraphFromFile(){
		Graph graph = fileGraphCreator.createGraph("src/test/resources/test-graph.txt");
		Station stationA = graph.getStation('A');
		assertNotNull(stationA);
		assertEquals(stationA.getDestinations().size(),2);
		assertEquals(stationA.getDistanceTo('B'),5);
		assertEquals(stationA.getDistanceTo('C'),4);
		Station stationB = graph.getStation('B');
		assertNotNull(stationB);
		assertEquals(stationB.getDistanceTo('D'),12);
	}
	
	public void canCreateGraphFromMultilineFile(){
		Graph graph = fileGraphCreator.createGraph("src/test/resources/multiline-test-graph.txt");
		Station stationA = graph.getStation('A');
		assertNotNull(stationA);
		assertEquals(stationA.getDestinations().size(),2);
		assertEquals(stationA.getDistanceTo('B'),5);
		assertEquals(stationA.getDistanceTo('C'),4);
		Station stationB = graph.getStation('B');
		assertNotNull(stationB);
		assertEquals(stationB.getDistanceTo('D'),8);
	}
	
	@Test(expectedExceptions=RuntimeException.class)
	public void throwsRuntimeExceptionWhenNoFile(){
		fileGraphCreator.createGraph("missing");
	}
	
	@Test(expectedExceptions=RuntimeException.class, expectedExceptionsMessageRegExp="Wrong graph format: .*")
	public void throwsRuntimeExceptionWhenWrongFormat(){
		fileGraphCreator.createGraph("src/test/resources/wrong-format-graph.txt");
	}

}
