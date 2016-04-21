package org.usfirst.frc.team449.robot.drive.tank.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FaceLeftGoal extends CommandGroup {
	public FaceLeftGoal() {
		addSequential(new TurnAngle(15)); // TODO actually calculate this
											// constant and externalize it
	}
}
