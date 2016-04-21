package org.usfirst.frc.team449.robot.drive.tank.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FaceFront extends CommandGroup {
	public FaceFront() {
		addSequential(new TurnAngle(0));
	}
}
