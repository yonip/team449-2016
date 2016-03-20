package org.usfirst.frc.team449.robot.commands;

import org.usfirst.frc.team449.robot.mechanism.intake.commands.IntakeDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * <code>CommandGroup</code> running robot during the autonomous period
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-03-11
 *
 */
public class Auto extends CommandGroup {
	public Auto() {
		addSequential(new IntakeDown());
	}
}
