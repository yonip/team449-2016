package org.usfirst.frc.team449.robot.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.drive.tank.commands.DriveDistance;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachChivald;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachPortcullis;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * <code>CommandGroup</code> running robot during the autonomous period
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-03-11
 *
 */
public class Autonomous extends CommandGroup {
	/**
	 * Instantiate a new <code>Autonomous</code>
	 */
	public Autonomous() {
		executeCross();
	}

	/**
	 * Cross the defense and stay on the other side
	 */
	private void executeCross() {
		driveToDefense(); // get to defense
		breachDefense(); // breach defense
		// TODO fix and externalize constants
		addSequential(new DriveDistance(1d)); // get a little further
	}

	/**
	 * Drive up to the defense
	 */
	private void driveToDefense() {
		// TODO fix and externalize constants
		addSequential(new DriveDistance(1d));
	}

	/**
	 * Breach the defense
	 */
	private void breachDefense() {
		switch (Robot.autoDefenseType) {
		case PORTCULLIS: {
			addSequential(new BreachPortcullis());
			break;
		}
		case CHEVAL_DE_FRISE: {
			addSequential(new BreachChivald());
			break;
		}
		default: {
			break;
		}
		}
	}
}
