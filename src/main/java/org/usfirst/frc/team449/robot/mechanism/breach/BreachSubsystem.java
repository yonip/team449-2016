package org.usfirst.frc.team449.robot.mechanism.breach;

import edu.wpi.first.wpilibj.DoubleSolenoid;
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
	 * The double solenoid for the back piston
	 */
	private DoubleSolenoid backSolenoid;

	/**
	 * The double solenoid for the front piston
	 */
	private DoubleSolenoid frontSolenoid;

	/**
	 * Instantiate a new <code>BreachSubsystem</code>
	 */
	public BreachSubsystem() {
		backSolenoid = new DoubleSolenoid(BreachMap.SOLENOID_BACK_FORWARD_PORT, BreachMap.SOLENOID_BACK_REVERSE_PORT);
		frontSolenoid = new DoubleSolenoid(BreachMap.SOLENOID_FRONT_FORWARD_PORT,
				BreachMap.SOLENOID_FRONT_REVERSE_PORT);
	}

	/**
	 * Set the back double solenoid valve to its forward state
	 */
	public void setBackSolenoidForward() {
		backSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	/**
	 * Set the back double solenoid valve to its reverse state
	 */
	public void setBackSolenoidReverse() {
		backSolenoid.set(DoubleSolenoid.Value.kReverse);
	}

	/**
	 * Set the front double solenoid valve to its forward state
	 */
	public void setFrontSolenoidForward() {
		frontSolenoid.set(DoubleSolenoid.Value.kForward);
	}

	/**
	 * Set the front double solenoid valve to its reverse state
	 */
	public void setFrontSolenoidReverse() {
		frontSolenoid.set(DoubleSolenoid.Value.kReverse);
	}

	@Override
	protected void initDefaultCommand() {
	}
}
