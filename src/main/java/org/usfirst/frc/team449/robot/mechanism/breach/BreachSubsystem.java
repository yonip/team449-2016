package org.usfirst.frc.team449.robot.mechanism.breach;

import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.mechanism.MechanismSubsystem;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;

public class BreachSubsystem extends MechanismSubsystem {

	VictorSP motor;
	DigitalInput limitSwitchUpper;
	DigitalInput limitSwitchLower;

	public BreachSubsystem(RobotMap map) {
		super(map);
		System.out.println("Breacher init started");

		if (!(map instanceof BreachMap)) {
			System.err.println("Breach has a map of class " + map.getClass().getSimpleName() + " and not BreachMap");
		}

		this.motor = new VictorSP(((BreachMap) map).motor.PORT);
		this.motor.setInverted(((BreachMap) map).motor.INVERTED);

		this.limitSwitchUpper = new DigitalInput(((BreachMap) map).upper.PORT);
		this.limitSwitchLower = new DigitalInput(((BreachMap) map).lower.PORT);

		System.out.println("Breacher init finished");
	}

	@Override
	protected void initDefaultCommand() {

	}

}
