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
		((TankDriveSubsystem) Robot.drive).enableAngleController();
		((TankDriveSubsystem) Robot.drive).setTurnToAngle(theta);
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return ((TankDriveSubsystem) Robot.drive).getTurnAngleDone();
	}

	@Override
	protected void end() {
		System.out.println("TurnAngle end");

		((TankDriveSubsystem) Robot.drive).disableAngleController();
	}

	@Override
	protected void interrupted() {
		System.out.println("TurnAngle interrupted");
		((TankDriveSubsystem) Robot.drive).disableAngleController();
	}
}
