package org.usfirst.frc.team449.robot.commands;

import org.usfirst.frc.team449.robot.drive.tank.commands.DriveDistance;

import edu.wpi.first.wpilibj.command.Command;

/**
 * <code>CommandGroup</code> running robot during the autonomous period
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-03-11
 *
 */
public class StayForTimeout extends Command {
	public StayForTimeout(double timeout) {
		super(timeout);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return this.isTimedOut();
	}

	@Override
	protected void end() {		
	}

	@Override
	protected void interrupted() {
	}
}
