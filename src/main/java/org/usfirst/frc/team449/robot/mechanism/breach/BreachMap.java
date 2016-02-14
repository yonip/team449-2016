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
	 * Port number of the back solenoid's forward state
	 */
	public static final int SOLENOID_BACK_FORWARD_PORT = 0;

	/**
	 * Port number of the back solenoid's reverse state
	 */
	public static final int SOLENOID_BACK_REVERSE_PORT = 1;

	/**
	 * Port number of the front solenoid's forward state
	 */
	public static final int SOLENOID_FRONT_FORWARD_PORT = 2;

	/**
	 * Port number of the front solenoid's reverse state
	 */
	public static final int SOLENOID_FRONT_REVERSE_PORT = 3;
	
	/**
	 * Distance to drive to get to breach
	 */
	public static final double BREACH_DISTANCE = 1; //TODO set this distance
}
