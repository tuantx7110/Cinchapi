package com.cinchapi;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * This class will use Unsafe tool to manage an array off heap.
 * This behaves similarry with ArratList in Java core.
 *  
 *
 */
public class DirectArray {
	public long capacity;
	public static long memoryAddress;
	public long length = 0;;
	private Unsafe unsafe;

	public DirectArray(long capacity) {
		this.capacity = capacity;
		unsafe = getUnsafe();
		memoryAddress = unsafe.allocateMemory(capacity * Long.BYTES);
		// unsafe.setMemory(memoryAddress,capacity*Long.BYTES,(byte)0);
	}

	/**
	 * Get Unsafe instance
	 * 
	 * @return
	 */

	private Unsafe getUnsafe() {
		try {
			Field singleoneInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
			singleoneInstanceField.setAccessible(true);
			return (Unsafe) singleoneInstanceField.get(null);
		} catch (IllegalArgumentException | SecurityException | NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * return memory address from {@code }}index
	 * 
	 * @param index
	 * @return memory address
	 */

	private long convert2Address(long index) {
		return (memoryAddress + index * Long.BYTES);
	}

	/**
	 * Put {@code value} into array at {@code index}
	 * 
	 * @param index
	 * @param value
	 */
	public void putValue(long index, long value) {
		long address = convert2Address(index);
		unsafe.putLong(address, value);
	}
	
	

	/**
	 * Return value at {@code }}index in the array
	 * 
	 * @param index
	 * @return value at {@code }}index
	 */
	public long getValue(long index) {
		long address = convert2Address(index);
		long value = unsafe.getLong(address);
		return value;
	}

	/**
	 * Attepmt to insert {@code value} into the collection and return
	 * {@code true} if the collection is modified after this method returns
	 * (e.g. {@code value} was not already contained in the collection)
	 * 
	 * @param value
	 *            a long value to insert into the collection
	 * @return {@code true} if {@code value} didn't previously exist in the
	 *         collection and is inserted
	 */
	public boolean insert(long value) {
		long i;
		if (length >= (capacity - 1)) {
			resize();
		}
		for (i = 0; i < length; i++) {

			if (getValue(i) == value)
				return false;
		}
		putValue(length, value);
		length = length + 1;
		return true;

	}

	/**
	 * Return {@code true} if {@code value} exists within the collection.
	 * 
	 * @param value
	 *            the value for which to check
	 * @return {@code true} if {@code value} is contained within the collection
	 */
	public boolean contains(long value) {
		long i;
		for (i = 0; i < length; i++) {
			
			if (getValue(i) == value)
				return true;
		}
		return false;
	}

	/**
	 * Resize the array when the number of elements reach capacity
	 */

	public void resize() {
		long newSize = (long) (capacity * 1.5);
		long newAddress = unsafe.allocateMemory(newSize * Long.BYTES);
		unsafe.copyMemory(memoryAddress, newAddress, length * Long.BYTES);
		unsafe.freeMemory(memoryAddress);
		capacity = newSize;
		memoryAddress = newAddress;
	}

	/**
	 * This function will occupied free memory
	 */
	public void destroy() {
		unsafe.freeMemory(memoryAddress);
	}

	/*
	 * this function will initialize the array when value from 0 to {@code
	 * limit}. The purpose of this function to test if this array can handle 10
	 * million values in JVM Heap RAM 64Mb
	 */
	public boolean initWithArray(long limit) {
		if (limit <=capacity) {
			for (long i = 0; i < limit; i++) {
				putValue(i,i);
				length=length+1;
			}
			return true;
		}
		return false;
	}

	public static void main(String args[]) {
		long capacity = 10;
		if (args.length > 0) {
			capacity = Integer.parseInt(args[args.length-1]);
		}
		long limit = capacity;
		DirectArray array = new DirectArray(capacity);
		if (array.initWithArray(limit)) {
			for (long i = 0; i < limit; i++) {
				if (!array.contains(i)) {
					
					System.out.println("run incorrectly");
					return;
				}
			}
			System.out.println("run correctly");
		}

		array.destroy();

	}

}
