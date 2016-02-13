package org.usfirst.frc.team449.robot.mechanism.breach.commands;

import org.usfirst.frc.team449.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command to set the breach arm to the stowed state
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-02-07
 *
 */
public class SetWedgeStowed extends Command {

	/**
	 * Instantiate a new <code>BreachStowed</code>
	 */
	public SetWedgeStowed() {
		requires(Robot.breach);
	}

	@Override
	protected void initialize() {
		System.out.println("BreachStowed init");
	}

	@Override
	protected void execute() {
		Robot.breach.setBackSolenoidReverse();
		Robot.breach.setFrontSolenoidReverse();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		System.out.println("BreachStowed end");
	}

	@Override
	protected void interrupted() {
		System.out.println("BreachStowed interupted");
	}
}
