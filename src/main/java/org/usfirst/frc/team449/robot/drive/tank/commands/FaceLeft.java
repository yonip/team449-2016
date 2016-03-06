package org.usfirst.frc.team449.robot.drive.tank.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FaceLeft extends CommandGroup {
	public FaceLeft() {
		addSequential(new TurnAngle(Math.PI / 2));
	}
}
