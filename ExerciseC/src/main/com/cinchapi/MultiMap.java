package com.cinchapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * Multiple Map allows to add new value to existing key.
 */
public class MultiMap {
	HashMap<String, List<String>> map;
	public MultiMap()
	{
		map= new HashMap<>();
	}
	
	/*
	 * Put new pair {@code key} and {@code value} to the {@code map}
	 *  
     * @param key the key element
     * @param value the value element
     * @return {@code true} if {@code key} and {@code value} pair is added to the {@code map}
     * return {@code false} if {@code key} and {@code value} pair is existing
	 */
	public boolean put(String key,String value)
	{
		if(key.equals(value))return false;
		if(map.containsKey(key))
		{
			if(!map.get(key).contains(value))
			{
				map.get(key).add(value);
				return true;
			}
			{
				return false;
			}
		}
		else
		{
			map.put(key, new ArrayList<String>());
			map.get(key).add(value);
			return true;
		}		
	}
	
	/*
	 * return collection of elements associate with {@code key}
	 */
	
	public List<String> get(String key)
	{
		return map.get(key);
	}
	
	
	

}
