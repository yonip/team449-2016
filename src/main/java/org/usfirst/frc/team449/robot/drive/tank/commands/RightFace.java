package org.usfirst.frc.team449.robot.drive.tank.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightFace extends CommandGroup {

	public RightFace() {
		addSequential(new TurnAngle(-Math.PI / 2));
	}
}
