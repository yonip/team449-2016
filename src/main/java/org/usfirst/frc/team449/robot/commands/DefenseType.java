package org.usfirst.frc.team449.robot.commands;

/**
 * Deprecated enumerate representing the possible defenses the robot will breach
 * during the autonomous period. Used to be used by {@link #Autonomous}
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-03-11
 *
 */
@Deprecated
public enum DefenseType {
	/**
	 * Breach the portcullis defense
	 */
	PORTCULLIS,

	/**
	 * Breach the cheval de frise defense
	 */
	CHEVAL_DE_FRISE,

	/**
	 * Breach the moat defense
	 */
	MOAT,

	/**
	 * Breach the ramparts defense
	 */
	RAMPARTS,

	/**
	 * Breach the drawbridge defense
	 */
	DRAWBRIDGE,

	/**
	 * Breach the sally port defense
	 */
	SALLY_PORT,

	/**
	 * Breach the rock wall defense
	 */
	ROCK_WALL,

	/**
	 * Breach the rough terrain defense
	 */
	ROUGH_TERRAIN,

	/**
	 * Breach the low bar defense
	 */
	LOW_BAR;
}
