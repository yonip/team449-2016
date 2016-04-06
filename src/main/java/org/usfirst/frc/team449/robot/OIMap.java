package org.usfirst.frc.team449.robot;

import org.json.JSONObject;

/**
 * a map of constants needed for any form of TankDrive or its subclasses, and
 * not defined higher in the hierarchy
 */

public class OIMap extends RobotMap {

	public int LEFT_DRIVE_STICK;

	public int RIGHT_DRIVE_STICK;

	// public int INTAKE_JOYSTICK;

	public int MAIN_CONTROLLER;

	public int MANUAL_OVERRIDES;
	
	public int BUTTON_PAD;

	public int INTAKE_IN;

	public int INTAKE_OUT;

	public int INTAKE_UP;

	public int INTAKE_DOWN;

	public int BREACH_CHIVAL;

	public int BREACH_PORTCULLIS;

	public int BREACH_CLOSE_PRIMARY;

	public int BREACH_CLOSE_SECONDARY;

	public int CAMERA_TOGGLE;

	public int DRIVE_STRAIGHT;

	public int IGNORE_IR;

	public int TOGGLE_PID;
	
    public int BP_INTAKE_OUT;
    
    public int BP_INTAKE_IN;
    
    public int BP_INTAKE_UP;
    
    public int BP_INTAKE_DOWN;
    
    public int BP_BREACH_CHIVAL;
    
    public int BP_BREACH_PORTCULLIS;
    
    public int BP_BREACH_CLOSE;
    
    public int BP_CAMERA_TOGGLE;

	public int ZERO_GYRO;

	public int FACE_FRONT;

	public int FACE_BACK;

	// public int FACE_LEFT;

	// public int FACE_RIGHT;

	public int FACE_LEFT_GOAL;

	public int FACE_RIGHT_GOAL;

	public double DEADBAND;

	public double POWER;

	public double MAX_VALUE;

	/**
	 * creates a new Map based on the configuration in the given json any maps
	 * in here are to be shared across all subsystems
	 *
	 * @param json
	 *            a JSONObject containing the configuration for the maps in this
	 *            object
	 */
	public OIMap(JSONObject json) {
		super(json);
	}
}
