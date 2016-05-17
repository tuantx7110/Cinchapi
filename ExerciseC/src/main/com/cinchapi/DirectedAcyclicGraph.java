package com.cinchapi;

import java.util.ArrayList;
import java.util.List;

public class DirectedAcyclicGraph {
	/**
	 * {@code edges}Store all the directed edges in the graph
	 */
	MultiMap edges;

	/**
	 * {@code vertexes} Store all vertexes in the graph
	 */
	List<String> vertexes;

	/**
	 * Construct an empty graph
	 */
	public DirectedAcyclicGraph() {
		edges = new MultiMap();
		vertexes = new ArrayList<String>();
	}

	/**
	 * Add new vertex
	 * 
	 * @param vertex
	 * @return {@code true} if vertex is added {@code false} if vertex is
	 *         existing
	 */
	public boolean addVertex(String vertex) {
		if (vertexes.contains(vertex))
			return false;
		else {
			vertexes.add(vertex);
			return true;
		}
	}

	/**
	 * check if there is directed path from {@code source} to
	 * {@code destination} Assume that both {@code source} and
	 * {@code destination} are vertexes in the graph
	 * 
	 * @param source
	 * @param destination
	 * @return true if there is directed path
	 */

	public boolean hasPath(String source, String destination) {
		if(source.equals(destination))return true;
		List<String> adjacentVertexList = edges.get(source);
		if (adjacentVertexList == null || adjacentVertexList.isEmpty())
			return false;
		
		
		for (String vertex : adjacentVertexList) {			
			
			if (hasPath(vertex, destination))
				return true;
		}
		return false;

	}

	/**
	 * Add an edge from {@code source} to {@code destination} and return
	 * {@code true} if the edge is added as a result of this function. If either
	 * {@code source} or {@code destination} does not exist, create them before
	 * adding the edge.
	 * 
	 * @param source
	 *            the name of the source node
	 * @param destination
	 *            the name of the destination edge
	 * @return {@code true} if the edge is added as a result of this method call
	 */
	public boolean addEdge(String source, String destination) {

		if (source.equals(destination))
			return false;

		if (!vertexes.contains(source)) {
			if (!vertexes.contains(destination)) {
				vertexes.add(source);
				vertexes.add(destination);
				edges.put(source, destination);
				return true;

			} else {
				vertexes.add(source);
				edges.put(source, destination);
				return true;
			}
		}

		if (!vertexes.contains(destination)) {
			vertexes.add(destination);
			edges.put(source, destination);
			return true;
		}
		
		if(!hasPath(destination,source ))
		{
			edges.put(source, destination);
			return true;
		}	
		

		return false;

	}

}
