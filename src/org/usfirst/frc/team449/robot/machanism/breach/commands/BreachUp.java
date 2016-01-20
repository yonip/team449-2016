package org.usfirst.frc.team449.robot.machanism.breach.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.breach.BreachMap;
import org.usfirst.frc.team449.robot.breach.BreachSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command to raise the breach arm to the "up" position
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-01-20
 *
 */
public class BreachUp extends Command {

	/**
	 * Instantiate a new <code>BreachUp</code>
	 */
	public BreachUp() {
		requires(Robot.breach);
	}

	protected void initialize() {
		System.out.println("BreachUp init");
	}

	protected void execute() {
		Robot.breach.set(BreachMap.MOTOR_SPEED_UP);
	}

	protected boolean isFinished() {
		return Robot.breach.getLimitSwitchUpperValue();
	}

	protected void end() {
		System.out.println("BreachUp end");
	}

	protected void interrupted() {
		System.out.println("BreachUp interupted");
	}
}
