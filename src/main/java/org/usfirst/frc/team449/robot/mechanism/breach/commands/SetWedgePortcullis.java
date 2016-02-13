package org.usfirst.frc.team449.robot.mechanism.breach.commands;

import org.usfirst.frc.team449.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command to set the breach arm to the portcullis state
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-02-07
 *
 */
public class SetWedgePortcullis extends Command {

	/**
	 * Instantiate a new <code>BreachPortcullis</code>
	 */
	public SetWedgePortcullis() {
		requires(Robot.breach);
	}

	@Override
	protected void initialize() {
		System.out.println("BreachPortcullis init");
	}

	@Override
	protected void execute() {
		Robot.breach.setBackSolenoidForward();
		Robot.breach.setFrontSolenoidForward();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		System.out.println("BreachPortcullis end");
	}

	@Override
	protected void interrupted() {
		System.out.println("BreachPortcullis interupted");
	}
}
