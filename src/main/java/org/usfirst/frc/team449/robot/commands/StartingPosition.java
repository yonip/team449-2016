package org.usfirst.frc.team449.robot.commands;

/**
 * Enumerate representing the possible starting positions of the robot
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-02-11
 *
 */
public enum StartingPosition {
	/**
	 * Outer left starting position
	 */
	LEFT_OUTER,

	/**
	 * Inner left starting position
	 */
	LEFT_INNER,

	/**
	 * Center starting position
	 */
	CENTER,

	/**
	 * Inner right starting position
	 */
	RIGHT_INNER,

	/**
	 * Inner right starting position
	 */
	RIGHT_OUTER;

	/**
	 * Get the starting position as an int
	 * 
	 * @return starting position as an int (0 to 4 from left to right)
	 */
	public int toInt() {
		switch (this) {
		case LEFT_OUTER:
			return 0;
		case LEFT_INNER:
			return 1;
		case CENTER:
			return 2;
		case RIGHT_INNER:
			return 3;
		case RIGHT_OUTER:
			return 4;
		default:
			break;
		}
		return -1;
	}
}
