package org.usfirst.frc.team449.robot.mechanism.breach.commands;

import org.usfirst.frc.team449.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * {@link Command}that sets the breach arm to the state for breaching the
 * chivald de fries
 */
public class BreachChivald extends Command {
	/**
	 * Instantiate a new <code>BreachChivald</code>, taking control of the
	 * breach subsystem.
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
		Robot.breach.setSolenoid(true, false);
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
