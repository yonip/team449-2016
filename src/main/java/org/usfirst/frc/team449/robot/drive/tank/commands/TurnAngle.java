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
		TankDriveSubsystem drive = (TankDriveSubsystem) Robot.drive;
		TankDriveMap map = (TankDriveMap) drive.map;
		// the drive is velocity based, not position based, so this won't work
		// also the drive subsystem has the set(double, double) method for a reason (ie dont make fields public)
		//drive.rightCluster.setSetpoint(map.RADIUS * theta / 2);
		//drive.leftCluster.setSetpoint(-map.RADIUS * theta / 2);
		// TODO: make this actually work
	}

	@Override
	protected boolean isFinished() {
		TankDriveSubsystem drive = (TankDriveSubsystem) Robot.drive;
		TankDriveMap map = (TankDriveMap) drive.map;
		// don't make fields public
		//double rcDistance = Math.abs(drive.rightCluster.getSetpoint() - drive.leftCluster.getPosition());
		//double lcDistance = Math.abs(drive.leftCluster.getSetpoint() - drive.leftCluster.getPosition());
		//return (rcDistance < map.ZERO_TOL) && (lcDistance < map.ZERO_TOL);
		// also that's not what ZERO_TOL is for
		// TODO: make this actually work
		return false;
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
