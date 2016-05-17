package com.cinchapi;

import static org.junit.Assert.*;

import org.junit.Test;

public class DirectedAcyclicGraphTest {

	@Test
	public void testAddVertex() {
		DirectedAcyclicGraph graph=new DirectedAcyclicGraph();
		
		assertEquals("add vertex fail when there is no existing vertex", true,graph.addVertex("A"));
		assertEquals("add vertex fail when there is existing vertex", false,graph.addVertex("A"));
		
	}

	@Test
	public void testHasPath() {
		DirectedAcyclicGraph graph=new DirectedAcyclicGraph();
		graph.addVertex("1");
		graph.addVertex("2");
		graph.addVertex("3");
		graph.addVertex("4");
		graph.addEdge("1", "2");
		graph.addEdge("2", "4");
		
		assertEquals("hasPaht fail when there is no path", false,graph.hasPath("1","3"));
		assertEquals("hasPaht fail when there is existing path",true, graph.hasPath("1","4"));
	}

	@Test
	public void testAddEdge() {
		DirectedAcyclicGraph graph=new DirectedAcyclicGraph();
		assertEquals("AddEdge fail when there are not source and destination", true,graph.addEdge("1", "2"));
		assertEquals("AddEdge fail when there is not destination but have existing source", true,graph.addEdge("2", "3"));
		assertEquals("AddEdge fail when there is existing destination but have not source", true,graph.addEdge("6", "3"));
		assertEquals(true,graph.addEdge("4", "6"));
		assertEquals(true,graph.addEdge("4", "5"));
		assertEquals(true,graph.addEdge("5", "6"));
		assertEquals("AddEdge fail when there is no directed path",true,graph.addEdge("2", "4"));
		assertEquals("AddEdge fail when there is directed path",false,graph.addEdge("5", "1"));
		assertEquals("AddEdge fail when there is directed path",false,graph.addEdge("6", "1"));
		
		
		
	}

}
