package org.usfirst.frc.team449.robot.mechanism.breach;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the defense breaching arm
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-01-20
 *
 */
public class BreachSubsystem extends Subsystem {

	/**
	 * The breach arm motor
	 */
	private Talon motor;

	/**
	 * The upper limit switch (tells whether the arm is in the "up" position
	 */
	private DigitalInput limitSwitchUpper;

	/**
	 * The lower limit switch (tells whether the arm is in the "down" position
	 */
	private DigitalInput limitSwitchLower;

	/**
	 * Instantiate a new <code>BreachSubsystem</code>
	 */
	public BreachSubsystem() {
		motor = new Talon(BreachMap.MOTOR_PORT);
		DigitalInput limitSwitchUpper = new DigitalInput(
				BreachMap.LIMIT_UPPER_PORT);
		DigitalInput limitSwitchLower = new DigitalInput(
				BreachMap.LIMIT_LOWER_PORT);
	}

	/**
	 * Set the speed of the breach arm motor
	 * 
	 * @param speed
	 *            the normalized speed of the motor
	 */
	public void set(double speed) {
		motor.set(speed);
	}

	/**
	 * Get the state of the upper limit switch
	 * 
	 * @return whether the arm is in the "up" position
	 */
	public boolean getLimitSwitchUpperValue() {
		return limitSwitchUpper.get();
	}

	/**
	 * Get the state of the lower limit switch
	 * 
	 * @return whether the arm is in the "down" position
	 */
	public boolean getLimitSwitchLowerValue() {
		return limitSwitchLower.get();
	}

	@Override
	protected void initDefaultCommand() {
		// nothing here folks
	}

}
