package org.usfirst.frc.team449.robot.drive.tank.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.drive.components.Rangefinder;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveMap;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveSubsystem;

public class DriveDistance extends Command {
	private double distance;
	private int counter;
	private boolean forward;
	private boolean crashProtected;

	/**
	 * Create a DriveDistinace command
	 * Will not stop for obstacles
	 * @param distance - distance to drive
	 */
	public DriveDistance(double distance) {
		requires(Robot.drive);

		if (distance < 0) {
			this.distance = -distance;
			forward = false;
		} else {
			this.distance = -distance;
			forward = true;
		}

		crashProtected = false;
	}

	/**
	 * Create a DriveDistance command
	 * @param distance - distance to drive
	 * @param crashProtected - true = will stop for obstacles
	 */
	public DriveDistance(double distance, boolean crashProtected) {
		requires(Robot.drive);

		if (distance < 0) {
			this.distance = -distance;
			forward = false;
		} else {
			this.distance = -distance;
			forward = true;
		}

		this.crashProtected = crashProtected;
	}

	protected void initialize() {
		counter = 0;
		System.out.println("DriveDistance init");
	}

	@Override
	protected void execute() {
		double leftThrottle = TankDriveMap.AUTO_SPEED;
		double rightThrottle = TankDriveMap.AUTO_SPEED;

		if (crashProtected && ((TankDriveSubsystem) Robot.drive).rangefinder.getTooClose()) {
			System.out.println("DriveDistance waiting; path obstructed by obstacle");
			return;
		}

		if (forward) {
			((TankDriveSubsystem) Robot.drive).setThrottle(leftThrottle, rightThrottle);
		} else {
			((TankDriveSubsystem) Robot.drive).setThrottle(-leftThrottle, -rightThrottle);
		}

		counter++;
	}

	@Override
	protected boolean isFinished() {
		if (counter * TankDriveMap.DIST_CONV > distance) {
			return true;
		}
		return false;
	}

	@Override
	protected void end() {
		((TankDriveSubsystem) Robot.drive).setThrottle(0, 0);
		System.out.println("DriveDistance end");
	}

	@Override
	protected void interrupted() {
		((TankDriveSubsystem) Robot.drive).setThrottle(0, 0);
		System.out.println("DriveDistance interrupted");
	}
}
