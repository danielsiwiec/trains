package com.danielsiwiec.trains;

import java.util.List;

public class OutputCreator {
	
	private static final String NO_ROUTE_OUTPUT = "NO SUCH ROUTE";

	public String createOutput(List<Integer> results){
		StringBuilder sb = new StringBuilder();
		int resultNo = 0;
		for (Integer result : results){
			sb.append("Output #");
			sb.append(++resultNo);
			sb.append(": ");
			sb.append(result != null ? result : NO_ROUTE_OUTPUT);
			sb.append("\n");	
		}
		return sb.toString();
	}
}
