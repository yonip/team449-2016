package org.usfirst.frc.team449.robot.drive.tank.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ZeroGyro extends Command {

	public ZeroGyro() {
		requires(Robot.drive);
	}

	public void execute() {
		((TankDriveSubsystem) Robot.drive).zeroGyro();
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}
