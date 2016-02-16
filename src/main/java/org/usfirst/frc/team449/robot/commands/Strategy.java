package org.usfirst.frc.team449.robot.commands;

/**
 * Enumerate representing the possible strategies to be executed during the
 * autonomous period
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-02-16
 *
 */
public enum Strategy {

	/**
	 * Find the ball and retrieve it from the middle of the field
	 */
	GET,

	/**
	 * Cross the defense and stay on the other side
	 */
	CROSS,

	/**
	 * Cross the defense, about face, and drive back, crossing the defense a
	 * second time
	 */
	CROSS_RETURN,

	/**
	 * Cross the defense, about face, eject the ball, and drive back, crossing
	 * the defense a second time
	 */
	CROSS_EJECT_RETURN,

	/**
	 * Cross the defense, about face, and get ready to shoot at a low goal
	 */
	CROSS_PREPARE_SHOT,

	/**
	 * Cross the defense, about face, and shoot at a low goal
	 */
	CROSS_SHOOT;
}
