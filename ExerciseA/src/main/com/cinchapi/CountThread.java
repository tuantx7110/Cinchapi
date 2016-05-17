package com.cinchapi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CountThread extends Thread {
	List<Integer> collection;
	CyclicBarrier barrier;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
