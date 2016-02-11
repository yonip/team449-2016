package org.usfirst.frc.team449.robot.drive.tank.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AboutFace extends CommandGroup {

	public AboutFace() {
		addSequential(new TurnAngle(Math.PI));
	}
}
