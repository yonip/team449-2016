package org.usfirst.frc.team449.robot.mechanism.intake;

import org.json.JSONObject;
import org.usfirst.frc.team449.robot.mechanism.MechanismMap;

/**
 * a map of constants needed for any form of Drive or its subclasses, and not defined higher in the hierarchy
 */
public class IntakeMap extends MechanismMap {
    public Motor motor;
    public double OUTPUT_SPEED;
    public double INPUT_SPEED;

    /**
     * creates a new Intake Map based on the configuration in the given json
     * any maps in here are to be shared across all intake subsystems
     * @param json a JSONObject containing the configuration for the maps in this object
     */
    public IntakeMap(JSONObject json) {
        super(json);
    }
}
