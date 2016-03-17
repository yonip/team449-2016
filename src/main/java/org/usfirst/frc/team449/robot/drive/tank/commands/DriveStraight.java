package org.usfirst.frc.team449.robot.drive.tank.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveMap;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraight extends Command {
	double leftThrottle;
	double rightThrottle;

	public DriveStraight() {
		requires(Robot.drive);
		System.out.println("Drive Robot bueno");
	}

	@Override
	protected void initialize() {
		((TankDriveSubsystem) Robot.drive).enableDriveStraightCorrector();
		SmartDashboard.putBoolean("straigt on", true);
	}

	@Override
	protected void execute() {
		//leftThrottle = Robot.oi.getDriveAxisLeft() * ((TankDriveMap) (Robot.drive.map)).leftCluster.speed;
		rightThrottle = Robot.oi.getDriveAxisRight() * ((TankDriveMap) (Robot.drive.map)).rightCluster.speed;
		((TankDriveSubsystem) Robot.drive).setThrottle(rightThrottle, rightThrottle);
		SmartDashboard.putNumber("Distance", ((TankDriveSubsystem) Robot.drive).getDistance());
		SmartDashboard.putBoolean("straigt on", true);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		((TankDriveSubsystem) Robot.drive).disableDriveStraightCorrector();
		SmartDashboard.putBoolean("straigt on", false);
	}

	@Override
	protected void interrupted() {
		((TankDriveSubsystem) Robot.drive).disableDriveStraightCorrector();
		SmartDashboard.putBoolean("straigt on", false);
		
		
	}
}
