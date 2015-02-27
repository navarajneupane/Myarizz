/**
 * 
 */
package com.myarizz.navaraj;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nneupane
 *
 */
public class ActionHandler {
	
	/**
	 * @param args
	 * This method will receive list containing player's detail and calculate the points and send back to frontend.
	 * TODO Define interface to receive input from frontend. Modify method return type to handle data to be sent to frontend.
	 */
	public static void main(String[] args){
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();

		List<Object> player1 = new ArrayList<Object>();
		player1.add(5);
		player1.add("seen");
		player1.add("winner");
		
		List<Object> player2 = new ArrayList<Object>();
		player2.add(10);
		player2.add("seen");
		player2.add(null);
		
		List<Object> player3 = new ArrayList<Object>();
		player3.add(15);
		player3.add("seen");
		player3.add(null);
		
		map.put("Player1", player1);
		map.put("Player2", player2);
		map.put("Player3", player3);
		
		int totalPoints =0;
		for(Map.Entry<String, List<Object>> entry:map.entrySet()){
			
			String key = entry.getKey();
			List<Object> values = entry.getValue();
			int pointOfEachPlayer = (int) values.get(0); 
			totalPoints += pointOfEachPlayer;
			
			System.out.println("Key:"+key);
			System.out.println("Values:"+values);
		}
		System.out.println("Total Points:"+totalPoints);
	}
}
