package org.usfirst.frc.team449.robot.mechanism.intake.commands;

import org.usfirst.frc.team449.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * {@link Command} that lowers the intake mechanism on its two piston supports.
 */
public class IntakeDown extends Command {

	/**
	 * Instantiate a new <code>IntakeDown</code>, taking control of the intake
	 * subsystem.
	 */
	public IntakeDown() {
		requires(Robot.intake);
	}

	@Override
	protected void initialize() {
		System.out.println("IntakeDown init");
	}

	@Override
	protected void execute() {
		Robot.intake.setSolenoidReverse();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		System.out.println("IntakeDown end");
	}

	@Override
	protected void interrupted() {
		System.out.println("IntakeDown interupted");
	}
}
