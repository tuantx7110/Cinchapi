package com.cinchapi;

import static org.junit.Assert.*;

import org.junit.Test;

public class DirectArrayTest {

	@Test
	public void testInsert() {
		int capacity=10;
		DirectArray array =new DirectArray(capacity);
		
		assertEquals("Insert fail when there is no value",true, array.insert(10));
		assertEquals("Insert fail when there is existing value",false, array.insert(10));
		assertEquals("Insert fail when there is no existing value",true, array.insert(0));
		array.destroy();
	}

	@Test
	public void testContains() {
		int capacity=10;
		DirectArray array =new DirectArray(capacity);
		for(int i=0;i<5;i++)
		{
			array.insert(i);
		}	
		
		assertEquals("contains fail when there is existing value",true, array.contains(4));
		assertEquals("contains fail when there is no existing value",false, array.contains(15));
		array.destroy();
	}
	
	@Test
	public void testResize() {
		
		int capacity=100;
		DirectArray array =new DirectArray(capacity);
		for(int i=0;i<(capacity+5);i++)
		{
			array.insert(i);
		}	
		
		for(int i=0;i<(capacity+5);i++)
		{
			assertEquals("Resize fail when there is existing value",true, array.contains(i));
		}
		
		array.destroy();
		
	}

}
