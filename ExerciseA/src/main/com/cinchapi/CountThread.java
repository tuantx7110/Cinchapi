package com.cinchapi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * {@code CountThread} will add 1 to the global variable {@code Counter.count}
 * and add new value of {@code Counter.count} to its collection.
 * At the end, it will print all elements in its collection.
 */

public class CountThread extends Thread {
	/**
	 * {@code collection} will store all new values
	 */
	List<Integer> collection;
	
	/**
	 * {@code CyclicBarrier} is a synchronization mechanism to
	 * ensure that all {@code CountThread} start at the same time.
	 */
	
	CyclicBarrier barrier;
	
	/**
	 * {@code startTime} is the time that the {@code CountThread} starts
	 */
	
	long startTime;

	public List<Integer> getCollection() {
		return collection;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public void setCollection(List<Integer> collection) {
		this.collection = collection;
	}

	public CountThread(CyclicBarrier barrier) {
		collection = new ArrayList<Integer>();

		this.barrier = barrier;

	}
	/**
	 * This method is the core of the {@code CountThread}.
	 * It will add 1 to global variable {@code Counter.count} and 
	 * store new value to its collection.
	 * @see java.lang.Thread#run()
	 */

	public void run() {
		try {
			barrier.await();			
			startTime = System.currentTimeMillis();
			
			while (true) {

				if (!Counter.increase(this)) {
					System.out.println();
					String col="";
					for (Integer in : collection) {
						col=col+" "+in; 
					}
					System.out.println(col);
					break;
				}
				Thread.sleep((long) (Math.random()*100));
			}
		} catch (InterruptedException | BrokenBarrierException e) {
			
			e.printStackTrace();
		}

	}

}
