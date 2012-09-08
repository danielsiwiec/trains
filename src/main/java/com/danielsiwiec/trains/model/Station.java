package com.danielsiwiec.trains.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.danielsiwiec.trains.MissingRouteException;

public class Station {
	
	private Character id;
	private Map<Character,Route> routes = new HashMap<Character, Route>();
	private int distanceFromSource = Integer.MAX_VALUE;
	
	Station(char id) {
		this.id = id;
	}

	public Character getId(){
		return id;
	}
	
	public Route getRouteTo(Character destination){
		if (routes.containsKey(destination)){
			return routes.get(destination);
		}
		else {
			throw new MissingRouteException("Missing connection: " + id + "-" + destination);
		}
	}
	
	public int getDistanceTo(Character destination){
		return getRouteTo(destination).getDistance();
	}
	
	public Set<Station> getDestinations(){
		Set<Station> destinations = new HashSet<Station>();
		for (Route route : routes.values()){
			destinations.add(route.getDestination());
		}
		return destinations;
	}
	
	void addRoute(Route route) {
		routes.put(route.getDestination().id,route);		
	}

	public int getDistanceFromSource() {
		return distanceFromSource;
	}

	public void setDistanceFromSource(int distanceFromSource) {
		this.distanceFromSource = distanceFromSource;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Station other = (Station) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String toString(){
		return "Station " + id;
	}

}
