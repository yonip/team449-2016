package org.usfirst.frc.team449.robot.drive.tank.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * <code>Command</code> for driving until over an obstacle that causes the robot
 * to pitch greatly.
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-03-12
 *
 */
public class CrossByPitch extends Command {

	private double pitch;
	private boolean startedAscending;

	public CrossByPitch() {
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
		System.out.println("CrossByPitch init");
	}

	@Override
	protected void execute() {
		((TankDriveSubsystem) Robot.drive).setThrottle(1, 1);
		pitch = Math.abs(((TankDriveSubsystem) Robot.drive).getPitch());

		// if the robot just hit the obstacle, startedAscending = true
		if (!startedAscending && getPitched()) {
			startedAscending = true;
		}
	}

	@Override
	protected boolean isFinished() {
		// if the robot is flat after pitching, then the robot is back on the
		// ground
		return getPitched() && startedAscending;
	}

	@Override
	protected void end() {
		System.out.println("CrossByPitch end");
	}

	@Override
	protected void interrupted() {
		System.out.println("CrossByPitch interupted");
	}

	private boolean getPitched() {
		// TODO fix and externalize constants
		return pitch > 1;
	}
}
