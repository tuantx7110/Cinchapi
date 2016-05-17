package com.cinchapi;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Counter {
	public static int count = 0;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Waiting for all threads stop
		for (i = 0; i < countThreadList.length; i++) {
			countThreadList[i].join();
		}


	}

}
