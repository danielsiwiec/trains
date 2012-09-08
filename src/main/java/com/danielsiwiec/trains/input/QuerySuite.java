package com.danielsiwiec.trains.input;

import java.util.List;

import com.danielsiwiec.trains.GraphController;

public interface QuerySuite {

	public List<Integer> runQueries(GraphController controller);

}