package org.usfirst.frc.team449.robot.mechanism.intake;

import org.json.JSONObject;
import org.usfirst.frc.team449.robot.mechanism.MechanismMap;

/**
 * a map of constants needed for any form of Drive or its subclasses, and not defined higher in the hierarchy
 */
public class IntakeMap extends MechanismMap {
	public Motor motor;
	public DoubleSolenoid solenoid;
	public IRSensor leftIR;
	public IRSensor rightIR;
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

	public static class IRSensor extends MapObject {
		public int PORT;

		// TODO determine the voltage-to-distance scale factor
		/**
		 * The output voltage to actual distance scale factor (inches/Volt)
		 */
		public double SCALE_FACTOR;
		// TODO determine how far the ball should be from the US sensor before
		// stopping <code>IntakeIn</code>
		/**
		 * The distance between the infrared sensor and the ball at which
		 * <code>IntakeIn</code> stops
		 */
		public double IN_CLOSE_ENOUGH;
		
		public IRSensor(JSONObject json, String objPath, Class enclosing) {
			super(json, objPath, enclosing);
		}

	}

	
}
