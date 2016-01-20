package org.usfirst.frc.team449.robot.machanism.breach;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BreachSubsystem extends Subsystem {

	Talon motor = new Talon(BreachMap.MOTOR_PORT);
	DigitalInput limitSwitchUpper = new DigitalInput(BreachMap.LIMIT_UPPER_PORT);
	DigitalInput limitSwitchLower = new DigitalInput(BreachMap.LIMIT_LOWER_PORT);
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
}
