package org.usfirst.frc.team449.robot.drive.components;

import org.usfirst.frc.team449.robot.components.Component;
import org.usfirst.frc.team449.robot.drive.DriveMap;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * <code>Component</code> representing a rangefinding sensor telling the robot
 * if it is about to bump into something
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-02-12
 *
 */
public class Rangefinder extends Component {

	private AnalogInput sensor;

	/**
	 * Instantiate an <code>Rangefinder</code>
	 */
	public Rangefinder() {
		sensor = new AnalogInput(DriveMap.RANGEFINDER_PORT);
	}

	/**
	 * Get the distance measured by the rangefinder
	 * 
	 * @return distance measured by the rangefinder
	 */
	public double getDistance() {
		return sensor.getVoltage() * DriveMap.RANGEFINDER_SCALE_FACTOR;
	}

	/**
	 * Determine if the robot thinks is about to hit something (
	 * <code>getDistance()</code> returns a number less than an acceptable
	 * minimum)
	 * 
	 * @return whether the robot thinks it is going to hit something
	 */
	public boolean getTooClose() {
		return getDistance() < DriveMap.MINIMUM_SAFE_DISTANCE;
	}

	@Override
	public boolean getInverted() {
		return false;
	}

	@Override
	public void setInverted(boolean b) {
	}
}
