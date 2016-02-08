package org.usfirst.frc.team449.robot.mechanism.intake.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.mechanism.intake.IntakeMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Created by yonipedersen on 1/16/16.
 */
public class IntakeOut extends Command {

	public IntakeOut() {
		requires(Robot.intake);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.intake.setMotorSpeed(IntakeMap.INPUT_SPEED);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}
