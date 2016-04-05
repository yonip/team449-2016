package org.usfirst.frc.team449.robot.drive.tank.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveMap;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DefaultDrive extends Command {
	double leftThrottle;
	double rightThrottle;

	public DefaultDrive() {
		requires(Robot.drive);
		System.out.println("Drive Robot bueno");
	}

	@Override
	protected void initialize() {
		((TankDriveSubsystem) Robot.drive).reset();
	}

	@Override
	protected void execute() {
		leftThrottle = Robot.oi.getDriveAxisLeft() * ((TankDriveMap) (Robot.drive.map)).leftCluster.speed;
		rightThrottle = Robot.oi.getDriveAxisRight() * ((TankDriveMap) (Robot.drive.map)).rightCluster.speed;
		// pushing forward on the stick gives -1 so it is negated
		((TankDriveSubsystem) Robot.drive).setThrottle(leftThrottle, rightThrottle);
		SmartDashboard.putNumber("Distance", ((TankDriveSubsystem) Robot.drive).getDistance());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
		((TankDriveSubsystem) Robot.drive).setThrottle(Robot.oi.getDriveAxisLeft(), Robot.oi.getDriveAxisRight());
	}
}
