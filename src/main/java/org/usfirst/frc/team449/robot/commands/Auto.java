package org.usfirst.frc.team449.robot.commands;

import org.usfirst.frc.team449.robot.mechanism.breach.commands.*;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * <p>
 * This is the base autonomous period {@link CommandGroup} that other
 * <code>CommandGroup</code> inherit from. The auto code is structured this way
 * to fit the provided autonomous chooser radio button code.
 * </p>
 */
public class Auto extends CommandGroup {
	/**
	 * Instantiates a new <code>Auto</code>, lowering intake.
	 */
	public Auto() {
		addSequential(new IntakeUp());
		addSequential(new BreachStowed());
	}
}
