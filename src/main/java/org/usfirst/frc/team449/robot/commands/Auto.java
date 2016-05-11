package org.usfirst.frc.team449.robot.commands;

import org.usfirst.frc.team449.robot.mechanism.intake.commands.IntakeDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * <p>
 * This is the base autonomous period <code>CommandGroup</code> that other
 * <code>CommandGroup</code> inherit from. The auto code is structured this way
 * to fit the provided autonomous chooser radio button code.
 * </p>
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-03-11
 */
public class Auto extends CommandGroup {
	/**
	 * Instantiates a new <code>Auto</code>, lowering intake
	 */
	public Auto() {
		addSequential(new IntakeDown());
	}
}
