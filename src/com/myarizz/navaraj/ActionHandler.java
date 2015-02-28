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
	 * @param map
	 * 
	 * This method will receive list containing player's detail and calculate the points and send back to frontend.
	 * 
	 * @return map
	 */
	public static Map<String, Integer> sendInput(Map<String, List<Object>> map){
		
		int totalPoints = 0;
		int gamerPoint = 0;
		String winner = "";
		Map<String, Integer> mapPlayerPoint = new HashMap<String, Integer>();
		
		for(Map.Entry<String, List<Object>> entry:map.entrySet()){
			List<Object> values = entry.getValue();
			int pointOfEachPlayer = (int) values.get(0); 
			totalPoints += pointOfEachPlayer;
		}		
		
		for(Map.Entry<String, List<Object>> entry:map.entrySet()){
			
			List<Object> values = entry.getValue();
			int obtainedPoint = 0;
			
			if(values.get(1)=="seen" && values.get(2)!="winner"){
				obtainedPoint = (int)values.get(0)*values.size()-(totalPoints +3); 
				gamerPoint -=obtainedPoint;
				System.out.println("Key:"+entry.getKey()+ " Obtained Point:"+obtainedPoint);
				mapPlayerPoint.put(entry.getKey(), obtainedPoint);
			}
			else if(values.get(1)!="seen"){
				obtainedPoint = -(totalPoints +10);
				gamerPoint -=obtainedPoint;
				mapPlayerPoint.put(entry.getKey(), obtainedPoint);
				System.out.println("KeyNotSeen:"+entry.getKey()+ " Obtained Point:"+obtainedPoint);
			}
			else{
				winner = entry.getKey();
			}		
		}
		mapPlayerPoint.put(winner, gamerPoint);
		System.out.println("KeyWinner:"+winner+ " Obtained Point:"+gamerPoint);
		return mapPlayerPoint;
	}
	
	/**
	 * @param args
	 * 
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
		player3.add(0);
		player3.add(null);
		player3.add(null);
		
		map.put("A", player1);
		map.put("B", player2);
		map.put("C", player3);
		
		Map<String, Integer> mapPlayerPoint =sendInput(map);
		
		for(Map.Entry<String, Integer> entry:mapPlayerPoint.entrySet()){
			
			String key = entry.getKey();
			int pointOfEachPlayer = entry.getValue();
			
			System.out.println("Key:"+key);
			System.out.println("Values:"+pointOfEachPlayer);
		}
	}
	

}
