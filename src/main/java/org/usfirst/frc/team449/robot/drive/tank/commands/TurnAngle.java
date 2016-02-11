package org.usfirst.frc.team449.robot.drive.tank.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveMap;
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
		((TankDriveSubsystem) Robot.drive).rightCluster
				.setSetpoint(TankDriveMap.RADIUS * theta / 2);
		((TankDriveSubsystem) Robot.drive).leftCluster
				.setSetpoint(-TankDriveMap.RADIUS * theta / 2);
	}

	@Override
	protected boolean isFinished() {

		double rcDistance = Math
				.abs(((TankDriveSubsystem) Robot.drive).rightCluster
						.getSetpoint()
						- ((TankDriveSubsystem) Robot.drive).leftCluster
								.getPosition());
		double lcDistance = Math
				.abs(((TankDriveSubsystem) Robot.drive).leftCluster
						.getSetpoint()
						- ((TankDriveSubsystem) Robot.drive).leftCluster
								.getPosition());
		return (rcDistance < TankDriveMap.ZERO_TOL)
				&& (lcDistance < TankDriveMap.ZERO_TOL);
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
