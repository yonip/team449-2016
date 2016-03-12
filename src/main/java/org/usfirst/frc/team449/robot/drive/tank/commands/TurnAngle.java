package org.usfirst.frc.team449.robot.drive.tank.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class TurnAngle extends Command {

	private double theta;

	public TurnAngle(double theta) {
		requires(Robot.drive);
		this.theta = theta;
	}

	@Override
	protected void initialize() {
		System.out.println("TurnAngle init");
	}

	@Override
	protected void execute() {
		((TankDriveSubsystem) Robot.drive).setTurnToAngle(theta);
	}

	@Override
	protected boolean isFinished() {
		return ((TankDriveSubsystem) Robot.drive).getTurnAngleDone();
	}

	@Override
	protected void end() {
		System.out.println("TurnAngle end");
	}

	@Override
	protected void interrupted() {
		System.out.println("TurnAngle interrupted");
	}
}
