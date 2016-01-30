package org.usfirst.frc.team449.robot.mechanism.intake;

import org.json.JSONObject;
import org.usfirst.frc.team449.robot.mechanism.MechanismMap;

/**
 * a map of constants needed for any form of Drive or its subclasses, and not defined higher in the hierarchy
 */
public class IntakeMap extends MechanismMap {
    public final Motor motor;
    public final double OUTPUT_SPEED;
    public final double INPUT_SPEED;

    /**
     * creates a new Intake Map based on the configuration in the given json
     * any maps in here are to be shared across all intake subsystems
     * @param json a JSONObject containing the configuration for the maps in this object
     */
    public IntakeMap(JSONObject json) {
        super(json);
        String path = this.getPath();
        this.OUTPUT_SPEED = getDouble(path+".output_speed",json);
        this.INPUT_SPEED = getDouble(path+".input_speed", json);
        path += ".components";
        this.motor = new Motor(json, path+"motors.instances.motor");
    }

    public String getPath() {
        return super.getPath() + ".intake";
    }
}
