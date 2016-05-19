package org.usfirst.frc.team449.robot.mechanism.breach.commands;

import org.usfirst.frc.team449.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * <code>Command</code> that sets the breach arm to the state for breaching the chivald de fries
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

	/**
	 * Initialize the <code>Command</code>
	 */
	@Override
	protected void initialize() {
		System.out.println("BreachChivald init");
	}

	/**
	 * Sets the solenoids to the chivald (middle, (F, R)) position
	 */
	@Override
	protected void execute() {
		Robot.breach.setBackSolenoidForward();
		Robot.breach.setFrontSolenoidReverse();
	}

	/**
	 * Returns <code>true</code> to the Scheduler, as the <code>Command</code>
	 * is complete as soon as the <code>execute</code> is run, firing the solenoids.
	 */
	@Override
	protected boolean isFinished() {
		return true;
	}

	/**
	 * End the <code>Command</code>
	 */
	@Override
	protected void end() {
		System.out.println("BreachChivald end");
	}

	/**
	 * Interrupt the <code>Command</code>
	 */
	@Override
	protected void interrupted() {
		System.out.println("BreachChivald interupted");
	}
}
