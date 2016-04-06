package org.usfirst.frc.team449.robot.commands;

import org.json.JSONObject;
import org.usfirst.frc.team449.robot.RobotMap;

public class AutoMap extends RobotMap {

	public double AUTO_SHOOT_DISTANCE_1; // subtracted 2 feet
	public double AUTO_SHOOT_DISTANCE_2; // subtracted 1 foot
	public double AUTO_SHOOT_TURN_ANGLE;

	public AutoMap(JSONObject json) {
		super(json);
	}
}
