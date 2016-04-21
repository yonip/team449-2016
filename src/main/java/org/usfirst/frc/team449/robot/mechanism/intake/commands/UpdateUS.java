package org.usfirst.frc.team449.robot.mechanism.intake.commands;

import org.usfirst.frc.team449.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UpdateUS extends Command {

	public UpdateUS() {
		requires(Robot.intake);
	}

	@Override
	protected void initialize() {
		System.out.println("UpdateUs init");
	}

	@Override
	protected void execute() {
		Robot.intake.updateVals();
		SmartDashboard.putNumber("left", Robot.intake.getValLeft());
		SmartDashboard.putNumber("right", Robot.intake.getValRight());
		SmartDashboard.putNumber("angle", Robot.intake.getAngle());
		Robot.intake.findBall(); // for debugging ir
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		System.out.println("UpdateUs end; this Command should run for the"
				+ " duration of a robot run, so you (probably) done goofed");
	}

	@Override
	protected void interrupted() {
		System.out.println("UpdateUs interrupted");
	}

}
