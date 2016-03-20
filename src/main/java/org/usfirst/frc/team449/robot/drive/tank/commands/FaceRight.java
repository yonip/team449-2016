package org.usfirst.frc.team449.robot.drive.tank.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FaceRight extends CommandGroup {
	public FaceRight() {
		// TODO use this ...
		addSequential(new TurnAngle(-90));
	}
}
