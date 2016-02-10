package org.usfirst.frc.team449.robot.mechanism.intake.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.mechanism.intake.IntakeMap;

import edu.wpi.first.wpilibj.command.Command;


public class AutoIntakeOut extends Command {
	private int counter;
	
	
	public AutoIntakeOut() {
		requires(Robot.intake);
	}

	
	@Override
	protected void initialize() {
		counter = 0;
	}

	
	@Override
	protected void execute() {
		Robot.intake.set(IntakeMap.OUTPUT_SPEED);
		counter++;
	}

	
	@Override
	protected boolean isFinished() {
		if(counter > IntakeMap.SHOOT_TIME){
			return true;
		}
		return false;
	}

	
	@Override
	protected void end() {
		Robot.intake.set(0);
	}

	protected void interrupted() {
		Robot.intake.set(0);
	}
}
