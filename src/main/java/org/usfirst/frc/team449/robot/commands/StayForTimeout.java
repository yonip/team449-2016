package org.usfirst.frc.team449.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * {@link Command} used by some autonomous code to do nothing for a certain
 * number of seconds.
 */
public class StayForTimeout extends Command {
	/**
	 * Instantiates a new <code>StayForTimeout</code>, delaying a certain number
	 * of seconds.
	 * 
	 * @param timeout
	 *            number of seconds to delay for
	 */
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
