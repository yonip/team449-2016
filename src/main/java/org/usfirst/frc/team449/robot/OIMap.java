package org.usfirst.frc.team449.robot;

import org.json.JSONObject;

/**
 * a map of constants needed for any form of TankDrive or its subclasses, and not defined higher in the hierarchy
 */
public class OIMap extends RobotMap {

    public int LEFT_DRIVE_STICK = 1;

    public int RIGHT_DRIVE_STICK = 0;

    public int INTAKE_JOYSTICK = RIGHT_DRIVE_STICK;

    public int INTAKE_IN = 2;

    public int INTAKE_OUT = 3;

    /**
     * creates a new Map based on the configuration in the given json
     * any maps in here are to be shared across all subsystems
     *
     * @param json a JSONObject containing the configuration for the maps in this object
     */
    public OIMap(JSONObject json) {
        super(json);
    }
}