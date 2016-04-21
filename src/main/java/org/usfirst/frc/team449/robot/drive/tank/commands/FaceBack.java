package org.usfirst.frc.team449.robot.drive.tank.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FaceBack extends CommandGroup {
	public FaceBack() {
		addSequential(new TurnAngle(180));
	}
}
