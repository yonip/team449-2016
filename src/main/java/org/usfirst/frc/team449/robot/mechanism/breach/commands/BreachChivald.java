package org.usfirst.frc.team449.robot.mechanism.breach.commands;

import org.usfirst.frc.team449.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command to set the breach arm to the state for breaching the chivald de fries
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-02-07
 *
 */
public class BreachChivald extends Command {

	/**
	 * Instantiate a new <code>BreachChivald</code>
	 */
	public BreachChivald() {
		requires(Robot.breach);
	}

	@Override
	protected void initialize() {
		System.out.println("BreachChivald init");
	}

	@Override
	protected void execute() {
		Robot.breach.setBackSolenoidForward();
		Robot.breach.setFrontSolenoidReverse();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		System.out.println("BreachChivald end");
	}

	@Override
	protected void interrupted() {
		System.out.println("BreachChivald interupted");
	}
}
