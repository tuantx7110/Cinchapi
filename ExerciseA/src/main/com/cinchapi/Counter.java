package com.cinchapi;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Counter {
	public static int count = 0;
	
	/**
     * Increase the global variable {@code count} by 1 and add new value to
     * {@code counThread}. Return  {@code true} if {@code count} is increased.
     * return {@code false} if {@code count} is reached 100.
     * @param countThread the name of the source node
     * @return {@code true} if {@code count} is increased.
     * return {@code false} if {@code count} is reached 100.
     */
    
	public synchronized static boolean increase(CountThread countThread) {
		if (count < 100) {
			count = count + 1;
			System.out.println(count);
			countThread.getCollection().add(count);
			return true;
		}
		return false;
	}

	public static void main(String args[]) throws InterruptedException {
		int numberOfThreads = 3;
		int i;
		final CyclicBarrier barrier = new CyclicBarrier(numberOfThreads + 1);
		CountThread[] countThreadList = new CountThread[numberOfThreads];

		//Start thread
		for (i = 0; i < countThreadList.length; i++) {
			countThreadList[i] = new CountThread(barrier);
			countThreadList[i].start();
			
		}
		
		
		//Notify Threads to  start to count
		try {
			barrier.await();
		} catch (BrokenBarrierException e) {			
			e.printStackTrace();
		}
		
		
		//Waiting for all threads stop
		for (i = 0; i < countThreadList.length; i++) {
			countThreadList[i].join();
		}


	}

}
