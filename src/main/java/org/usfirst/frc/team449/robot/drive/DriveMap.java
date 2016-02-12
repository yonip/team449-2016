package org.usfirst.frc.team449.robot.drive;

/**
 * a map of constants needed for any form of Drive or its subclasses, and not
 * defined higher in the hierarchy
 */
public class DriveMap {
	/**
	 * Port number of the rangefinder's <code>AnalogInput</code>
	 */
	public static final int RANGEFINDER_PORT = 1;

	/**
	 * Rangefinder's output voltage to distance (in inches?) scale factor
	 */
	public static final int RANGEFINDER_SCALE_FACTOR = 1;

	/**
	 * Minimum distance (in inches?) measured by the rangefinder before the
	 * robot thinks it is about to hit something
	 */
	public static final double MINIMUM_SAFE_DISTANCE = 10.;
}