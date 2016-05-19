package org.usfirst.frc.team449.robot.mechanism;

import org.usfirst.frc.team449.robot.MappedSubsystem;
import org.usfirst.frc.team449.robot.RobotMap;

/**
 * <p>
 * This is the base class for all mechanisms on the robot. It extends
 * {@link #org.usfirst.frc.team449.robot.RobotMap} and contains a
 * {@link #org.usfirst.frc.team449.robot.mechanism.MechanismMap to
 * hold constants.
 * </p>
 */
public abstract class MechanismSubsystem extends MappedSubsystem {
	/**
	 * Instantiates a new <code>MechanismSubsystem</code> with a <code>RobotMap</code>
	 */
	public MechanismSubsystem(RobotMap map) {
		super(map);
	}
}
