package com.cinchapi;

import static org.junit.Assert.*;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.junit.Test;

public class CounterTest {

	/**
	 * Test whether all Count Thread start at the same time
	 */
	
	@Test	
	public void testStartTheSameTime() {
		int numberOfThreads = 3;
		int i;
		final CyclicBarrier gate = new CyclicBarrier(numberOfThreads + 1);
		CountThread[] countThreadList = new CountThread[numberOfThreads];

		for (i = 0; i < countThreadList.length; i++) {
			countThreadList[i] = new CountThread(gate);
			countThreadList[i].start();

		}
		try {
			gate.await();
		} catch (BrokenBarrierException | InterruptedException e) {
			
			e.printStackTrace();
		}

		try {
			for (i = 0; i < countThreadList.length; i++) {

				countThreadList[i].join();
			}
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}

		// Test if count threads started at the same time;
		long time = countThreadList[0].getStartTime();
		for (i = 1; i < countThreadList.length; i++) {

			assertEquals("not at the same time", time, countThreadList[0].getStartTime());
		}
	}

}
