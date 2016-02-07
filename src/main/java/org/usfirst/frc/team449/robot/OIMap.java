package org.usfirst.frc.team449.robot;

/**
 * a map of constants needed for any form of TankDrive or its subclasses, and
 * not defined higher in the hierarchy
 */
public class OIMap {
	/**
	 * the name of the subsystem associated with this map. used to retrieve from
	 * Robot's map of subsystems
	 */
	public static final String NAME = "OI";

	public static final int LEFT_DRIVE_STICK = 0;

	public static final int RIGHT_DRIVE_STICK = 1;

	public static final int INTAKE_JOYSTICK = RIGHT_DRIVE_STICK;

	public static final int INTAKE_IN = 2;

	public static final int INTAKE_OUT = 3;
}