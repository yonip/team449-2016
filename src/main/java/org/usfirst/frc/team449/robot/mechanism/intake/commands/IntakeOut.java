package org.usfirst.frc.team449.robot.mechanism.intake.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.mechanism.intake.IntakeMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A command to make push the ball out from the intake
 */
public class IntakeOut extends Command {

	public IntakeOut() {
		requires(Robot.intake);
	}

	@Override
	protected void initialize() {
		System.out.println("IntakeOut init");
	}

	@Override
	protected void execute() {
		Robot.intake.setMotorSpeed(((IntakeMap) (Robot.intake.map)).OUTPUT_SPEED);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		System.out.println("ItakeOut end");
	}

	@Override
	protected void interrupted() {
		Robot.intake.setMotorSpeed(0);
		System.out.println("ItakeOut interrupted");
	}
}
