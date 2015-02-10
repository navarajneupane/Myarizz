/**
 * 
 */
package com.myarizz.navaraj;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author navaraj
 * 
 */
public class Myarizz {

	/**
	 * No of decks
	 */
	private int deck;
	
	/**
	 * No of players
	 */
	private int players;
	
	/**
	 * Total points obtained 
	 */
	private int totalPoint;

	/**
	 * @author navaraj
	 * This enum keeps information of all possible points of myarizz.
	 */
	private enum Point {
		marriageAtHand(10), marriageAtFloor(15), tunnela(5), twoPointOne(2), twoPointTwo(
				5), twoPointThree(8), threePointOne(2), threePointTwo(5), threePointThree(
				8), fivePointOne(5), fivePointTwo(15), fivePointThree(25), alterTunnela(
				35), jokerTunnela(10), twoPointTunnela(20);

		private int value;

		private Point(int value) {
			this.value = value;
		}
	};

	/**
	 * TODO Switch Case. Implement it in a different way.
	 */

	public static void game() {
		int input;

		Scanner in = new Scanner(System.in);

		Point pointType[] = Point.values();
		ArrayList<String> pointTypeList = new ArrayList<String>();
		int i = 0;
		for (Point myPoint : pointType) {
			i++;
			pointTypeList.add(myPoint.toString());
			System.out.println(i + ". " + myPoint);
		}

		System.out.println("Enter the number of your point type:");
		input = Integer.parseInt(in.nextLine());
		String userInput = pointTypeList.get(input - 1);
		System.out.println(userInput + ":" + Point.valueOf(userInput).value+" points");

		switch (input) {
		case 1:
			//System.out.println(userInput + ":" + Point.valueOf(userInput));
			break;
		case 2:
			//System.out.println(userInput + ":" + Point.valueOf(userInput));
			break;
		case 3:
			break;
		case 4:
		}
	}
	
	/**
	 * @param args
	 * @author navaraj
	 * 
	 */
	public static void main(String[] args) {
		game();
	}
}
