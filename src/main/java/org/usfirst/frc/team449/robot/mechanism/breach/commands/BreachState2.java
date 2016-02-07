package org.usfirst.frc.team449.robot.mechanism.breach.commands;

import org.usfirst.frc.team449.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command to set the breach arm to the third state
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-02-07
 *
 */
public class BreachState2 extends Command {

	/**
	 * Instantiate a new <code>BreachState2</code>
	 */
	public BreachState2() {
		requires(Robot.breach);
	}

	@Override
	protected void initialize() {
		System.out.println("BreachState2 init");
	}

	@Override
	protected void execute() {
		Robot.breach.setBackSolenoidReverse();
		Robot.breach.setFrontSolenoidForward();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		System.out.println("BreachState2 end");
	}

	@Override
	protected void interrupted() {
		System.out.println("BreachState2 interupted");
	}
}
