package org.usfirst.frc.team449.robot.mechanism.intake.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.mechanism.intake.IntakeMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * {@link Command} that activates the intake mechanism to shoot a ball out.
 */
public class IntakeIn extends Command {
	/**
	 * Instantiate a new <code>IntakeIn</code>, taking control of the intake
	 * subsystem.
	 */
	public IntakeIn() {
		requires(Robot.intake);
	}

	@Override
	protected void initialize() {
		SmartDashboard.putBoolean("IntakeIn", true);
		System.out.println("IntakeIn init");
	}

	@Override
	protected void execute() {
		SmartDashboard.putBoolean("IntakeIn", true);
		Robot.intake.setMotorSpeed(((IntakeMap) (Robot.intake.map)).INPUT_SPEED / 2);
	}

	@Override
	protected boolean isFinished() {
		if (Robot.intake.isIgnoringIR())
			return false;
		else
			return Robot.intake.findBall();
	}

	@Override
	protected void end() {
		SmartDashboard.putBoolean("IntakeIn", false);
		Robot.intake.setMotorSpeed(0);
		System.out.println("IntakeIn end");
	}

	@Override
	protected void interrupted() {
		SmartDashboard.putBoolean("IntakeIn", false);
		Robot.intake.setMotorSpeed(0);
		System.out.println("ItakeIn interrupted");
	}
}
