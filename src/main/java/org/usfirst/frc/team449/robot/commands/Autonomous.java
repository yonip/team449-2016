package org.usfirst.frc.team449.robot.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.drive.tank.commands.CrossByPitch;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachChivald;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachPortcullis;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * <p>
 * Deprecated {@link CommandGroup} inteded to run the robot during the
 * autonomous period. This class contained methods for running a variety of
 * autonomous period procedures, however this class was replaced by the
 * {@link Auto} class and its subclasses.
 * </p>
 * 
 * @deprecated replaced by {@link Auto}
 */
@Deprecated
public class Autonomous extends CommandGroup {
	/**
	 * Instantiate a new <code>Autonomous</code>.
	 */
	public Autonomous() {
		executeCrossBlind();
	}

	/**
	 * Drive some distance through the obstacle (should work for obstacles
	 * without wheel slippage).
	 */
	private void executeCrossBlind() {
		driveToDefense(); // get to defense
		breachDefense(); // breach defense
		driveClearOfDefense(); // drive clear of the defense
	}

	/**
	 * Drive until the robot pitches and the returns to normal
	 */
	private void executeCrossByPitch() {
		driveToDefense();
		addSequential(new CrossByPitch());
		driveClearOfDefense();
	}

	/**
	 * Drive up to the defense
	 */
	private void driveToDefense() {
		// TODO fix and externalize constants
		// addSequential(new DriveDistance(1d));
	}

	/**
	 * Drive a safe distance away from the defense
	 */
	private void driveClearOfDefense() {
		// TODO fix and externalize constants
		// addSequential(new DriveDistance(1d));
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
