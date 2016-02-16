package org.usfirst.frc.team449.robot.commands;

/**
 * Enumerate representing the possible defenses the robot will breach during the
 * autonomous period
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-02-11
 *
 */
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