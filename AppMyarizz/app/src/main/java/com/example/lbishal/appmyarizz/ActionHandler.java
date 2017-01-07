/**
 * 
 */
package com.example.lbishal.appmyarizz;


import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nneupane
 *
 */
public class ActionHandler {
	@Override
	public String toString() {
		return super.toString();
	}

	/**
	 * @param map
	 * 
	 * This method will receive list containing player's detail and calculate the points and send back to frontend.
	 * 
	 * @return map
	 */
    static String TAG = "Action Handler";
	/*
	* Parameter 'map' consists of one key and three values, key is 'player's name <String>'
	*  and values are 'points<integer>', 'seen status<boolean>', and 'winner flag <boolean>'
	*
	* */
	public static HashMap<String, Integer> sendInput(HashMap<String, List<Object>> map){
		
		int totalPoints = 0;
		int gamerPoint  = 0;
		int pointOfEachPlayer = 0;
		String winner = "";
		HashMap<String, Integer> mapPlayerPoint = new HashMap<String, Integer>();
		
		for(Map.Entry<String, List<Object>> entry:map.entrySet()){
			List<Object> values = entry.getValue();
			if(!(boolean)values.get(1)){
				pointOfEachPlayer = 0; //If a player hasn't seen, his point won't be counted
			}
			else{
				pointOfEachPlayer = (int) values.get(0); // First index gives points
			}
			totalPoints += pointOfEachPlayer;
		}
        Log.d(TAG, "Total points is " + String.valueOf(totalPoints));

		//Winner must be selected and it can at most be 1
		if(winnerCounter(map)!=1){
			Log.d(TAG, "Invalid selection: Winner is either not selected or more than 1 is selected");
			//TODO: Send this information also to frontend

		}
		// 'Not seen' player cannot be 'Winner'
		else if(invalidWinner(map)){
			Log.d(TAG, "Invalid selection: 'Winner' has not 'Seen'");
			//TODO: Send this information also to frontend
		}
		else {
			for (Map.Entry<String, List<Object>> entry : map.entrySet()) {

				List<Object> values = entry.getValue();
				int obtainedPoint = 0;

				// Second index gives 'Seen flag' and third index gives "Winner flag"
				if (((boolean) values.get(1)) && (!(boolean) (values.get(2)))) { //seen but not winner
					obtainedPoint = (int) values.get(0) * values.size() - (totalPoints + 3);
					gamerPoint -= obtainedPoint;
					System.out.println("Key:" + entry.getKey() + " Obtained Point:" + obtainedPoint);
					mapPlayerPoint.put(entry.getKey(), obtainedPoint);
				} else if (!(boolean) values.get(1)) { //Not seen
					obtainedPoint = -(totalPoints + 10);
					gamerPoint -= obtainedPoint;
					mapPlayerPoint.put(entry.getKey(), obtainedPoint);
					System.out.println("KeyNotSeen:" + entry.getKey() + " Obtained Point:" + obtainedPoint);
				} else {
					winner = entry.getKey();
				}
			}
		}
		mapPlayerPoint.put(winner, gamerPoint);
		System.out.println("KeyWinner:"+winner+ " Obtained Point:"+gamerPoint);
		return mapPlayerPoint;
	}

	/*
	* This method is used to count number of winners. If there are no winners or if more than one winner
	* is selected exception should be thrown
	* */
	public static int winnerCounter(Map<String, List<Object>> map){
		int winner = 0;
		for(Map.Entry<String, List<Object>> entry:map.entrySet()){

			List<Object> values = entry.getValue();
			if((boolean)(values.get(2))){
				winner++;
			}
		}
		return winner;
	}

	/*
	* This method will check is winner selection is invalid, only 'Seen' can be 'Winner'
	*
	* */
	public static boolean invalidWinner(Map<String, List<Object>> map){
		boolean invalid = false;
		for(Map.Entry<String, List<Object>> entry:map.entrySet()){

			List<Object> values = entry.getValue();
			//Check is 'Not seen' and 'Winner' is selected at the same time
			if(!((boolean)values.get(1)) && ((boolean)(values.get(2)))){
				invalid = true;
			}
		}
		return invalid;
	}

	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args){
		HashMap<String, List<Object>> map = new HashMap<String, List<Object>>();

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
		
		HashMap<String, Integer> mapPlayerPoint =sendInput(map);
		
		for(Map.Entry<String, Integer> entry:mapPlayerPoint.entrySet()){
			
			String key = entry.getKey();
			int pointOfEachPlayer = entry.getValue();
			
			System.out.println("Key:"+key);
			System.out.println("Values:"+pointOfEachPlayer);
		}
	}
	

}
