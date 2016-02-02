package org.usfirst.frc.team449.robot.mechanism.breach;

/**
 * Constants for the BreachSubsystem
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-01-20
 *
 */
public class BreachMap {

	/**
	 * Port number of the breach arm motor
	 */
	public static final int MOTOR_PORT = 1;

	/**
	 * Port number of the upper limit switch
	 */
	public static final int LIMIT_UPPER_PORT = 2;

	/**
	 * Port number of the lower limit switch
	 */
	public static final int LIMIT_LOWER_PORT = 3;

	/**
	 * Motor speed for putting the arm in the "up" position
	 */
	public static final double MOTOR_SPEED_UP = 1;

	/**
	 * Motor speed for putting the arm in the "down" position
	 */
	public static final double MOTOR_SPEED_DOWN = -1;
}
