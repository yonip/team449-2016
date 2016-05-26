package org.usfirst.frc.team449.robot.mechanism.breach;

import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.mechanism.MechanismSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/** 
 *  This is the subsystem for the defense breaching arm. It extends
 * {@link #org.usfirst.frc.team449.robot.mechanism.MechanismSubsystem}.
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-01-20
 *
 */
public class BreachSubsystem extends MechanismSubsystem {
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
	public BreachSubsystem(RobotMap map) {
		super(map);
		System.out.println("Drive init started");
		if (!(map instanceof BreachMap)) {
			System.err.println("Breach has a map of class " + map.getClass().getSimpleName() + " and not BreachMap");
		}

		BreachMap breachMap = (BreachMap) map;
		backSolenoid = new DoubleSolenoid(breachMap.back.forward, breachMap.back.reverse);
		frontSolenoid = new DoubleSolenoid(breachMap.front.forward, breachMap.front.reverse);
	}

	/**
	 * Sets both solenoids to a specified state for each (true for forward, false for reverse).
	 */
	public void setSolenoid(boolean backSolForward, boolean frontSolForward) {
		if (backSolForward)
			backSolenoid.set(DoubleSolenoid.Value.kForward);
		else
			backSolenoid.set(DoubleSolenoid.Value.kReverse);
		if(frontSolForward)
			frontSolenoid.set(DoubleSolenoid.Value.kForward);
		else
			frontSolenoid.set(DoubleSolenoid.Value.kReverse);
	}

	@Override
	protected void initDefaultCommand() {
	}
}
