package org.usfirst.frc.team449.robot.mechanism.intake.commands;

import org.usfirst.frc.team449.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command to set the intake mechanism to its up state
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-02-08
 *
 */
public class IntakeUp extends Command {

	/**
	 * Instantiate a new <code>IntakeUp</code>
	 */
	public IntakeUp() {
		requires(Robot.intake);
	}

	@Override
	protected void initialize() {
		System.out.println("IntakeUp init");
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
		System.out.println("IntakeUp end");
	}

	@Override
	protected void interrupted() {
		System.out.println("IntakeUp interupted");
	}
}
