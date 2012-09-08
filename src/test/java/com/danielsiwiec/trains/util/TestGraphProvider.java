package com.danielsiwiec.trains.util;

import com.danielsiwiec.trains.model.Graph;

public class TestGraphProvider {
	
	public static Graph createGraph() {
		return new Graph.StringGraphBuilder().withRoute("AB1").withRoute("BA2").withRoute("BC2").
				withRoute("CA3").withRoute("CD5").withRoute("DA2").withRoute("AE2").withRoute("ED3").withRoute("FG4").build();
	}

}
