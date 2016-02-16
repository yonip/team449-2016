package org.usfirst.frc.team449.robot.mechanism.breach;

import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveMap;
import org.usfirst.frc.team449.robot.mechanism.MechanismSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the defense breaching arm
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
