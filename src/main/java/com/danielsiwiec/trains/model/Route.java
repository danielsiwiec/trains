package com.danielsiwiec.trains.model;

public class Route {
	
	private Station source;
	private Station destination;
	private int distance;
	
	Route(Station source, Station destination, int distance) {
		this.source = source;
		this.destination = destination;
		this.distance = distance;
	}

	public Station getSource() {
		return source;
	}

	public Station getDestination() {
		return destination;
	}
	
	public int getDistance(){
		return distance;
	}
	
	@Override
	public String toString(){
		return "From " + source + " to " + destination + ": " + distance; 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + distance;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (distance != other.distance)
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}
	
	

}
