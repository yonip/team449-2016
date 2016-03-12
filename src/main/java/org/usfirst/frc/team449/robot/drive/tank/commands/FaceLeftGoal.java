package org.usfirst.frc.team449.robot.drive.tank.commands;

import org.usfirst.frc.team449.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FaceLeftGoal extends CommandGroup {
	public FaceLeftGoal() {
		addSequential(new TurnAngle(RobotMap.leftGoalHeading));
	}
}
