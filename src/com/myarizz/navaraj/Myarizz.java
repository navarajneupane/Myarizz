/**
 * 
 */
package com.myarizz.navaraj;

import java.util.Scanner;

/**
 * @author nneupane
 *
 */
public class Myarizz {

	private int deck;
	private int players;
	private int totalPoint;

	/**
	 * @author nneupane
	 *This enum keeps information of all possible points of myarizz.
	 */
	private enum Point {
		marriageAtHand(10), marriageAtFloor(15), tunnela(5), twoPointOne(2), twoPointTwo(
				5), twoPointThree(8), threePointOne(2), threePointTwo(5), threePointThree(
				8), fivePointOne(5), fivePointTwo(15), fivePointThree(25), alterTunnela(35), jokerTunnela(10), twoPointTunnela(20);
		
		private int value;

		private Point(int value) {
			this.value = value;
		}
	};

	public static void main(String[] args) {
		Point pointType[] = Point.values();
		for(Point myPoint:pointType){
			System.out.println("Point:"+myPoint);
		}
		
		Point points = Point.tunnela;
		System.out.println("Point for marriage for hand is:"+points.value);
		
	}

	public static void game() {
		String input;
		
		Scanner in = new Scanner(System.in);
		
		
		System.out.println("Enter your point type:");
		input = in.nextLine();
		System.out.println("You entered:"+input);
	}


	// Currency usCoin = Currency.DIME;
	// switch (usCoin) {
	// case PENNY:
	// System.out.println("Penny coin");
	// break;
	// case NICKLE:
	// System.out.println("Nickle coin");
	// break;
	// case DIME:
	// System.out.println("Dime coin");
	// break;
	// case QUARTER:
	// System.out.println("Quarter coin");
	// }

}
