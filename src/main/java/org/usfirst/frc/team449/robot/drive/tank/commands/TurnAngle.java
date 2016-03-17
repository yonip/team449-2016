package org.usfirst.frc.team449.robot.drive.tank.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnAngle extends Command {

	private double theta;
	private boolean done;

	public TurnAngle(double theta) {
		requires(Robot.drive);
		this.theta = theta;
	}

	@Override
	protected void initialize() {
		System.out.println("TurnAngle init");
		((TankDriveSubsystem) Robot.drive).enableAngleController();
		((TankDriveSubsystem) Robot.drive).setTurnToAngle(theta);
		done = false;
		SmartDashboard.putBoolean("turnangle done", done);
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		done = ((TankDriveSubsystem) Robot.drive).getTurnAngleDone();
		SmartDashboard.putBoolean("turnangle done", done);
		return done;
	}

	@Override
	protected void end() {
		System.out.println("TurnAngle end");

		((TankDriveSubsystem) Robot.drive).disableAngleController();
		done = true;
		SmartDashboard.putBoolean("turnangle done", done);
	}

	@Override
	protected void interrupted() {
		System.out.println("TurnAngle interrupted");
		((TankDriveSubsystem) Robot.drive).disableAngleController();
		done = true;
		SmartDashboard.putBoolean("turnangle done", done);
	}
}
