package com.danielsiwiec.trains;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

@Test
public class OutputCreatorTest {
	
	OutputCreator processor = new OutputCreator();
	
	public void createsOutput(){
		List<Integer> results = createSimpleResults();
		String output = processor.createOutput(results);
		assertEquals(output,"Output #1: 1\nOutput #2: 3\n");
	}
	
	public void canTranslateNullResult(){
		List<Integer> results = createResultsWithNull();
		String output = processor.createOutput(results);
		assertEquals(output,"Output #1: NO SUCH ROUTE\n");
	}

	private List<Integer> createResultsWithNull() {
		return Arrays.asList((Integer)null);
	}

	private List<Integer> createSimpleResults() {
		return Arrays.asList(1,3);
	}
}
