package com.danielsiwiec.trains.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.danielsiwiec.trains.MissingRouteException;

public class Graph {

	private Map<Character, Station> stations = new HashMap<Character, Station>();
	
	public Station getStation(char id){
		if (!stations.containsKey(id)){
			throw new MissingRouteException("Missing station: " + id);
		}
		return stations.get(id);
	}
	
	public Collection<Station> getAllStations(){
		return stations.values();
	}

	private Graph(Map<Character, Station> stations) {
		this.stations = stations;
	}

	public static class StringGraphBuilder {

		private Map<Character, Station> stations = new HashMap<Character, Station>();

		public StringGraphBuilder withRoute(String route) {
			// assuming one-symbol stations
			char from = route.charAt(0);
			char to = route.charAt(1);
			int distance = Integer.parseInt(route.substring(2));
			addRoute(from, to, distance);
			return this;
		}

		private void addRoute(char fromId, char toId, int distance) {
			Station from = getOrCreate(fromId);
			Station to = getOrCreate(toId);
			Route route = new Route(from, to, distance);
			from.addRoute(route);
		}

		private Station getOrCreate(char id) {
			if (stations.containsKey(id)) {
				return stations.get(id);
			} else {
				Station station = new Station(id);
				stations.put(id, station);
				return station;
			}
		}

		public Graph build() {
			return new Graph(stations);
		}
	}
}
