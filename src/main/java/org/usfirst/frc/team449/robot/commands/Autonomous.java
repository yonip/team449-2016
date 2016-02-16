package org.usfirst.frc.team449.robot.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.drive.tank.commands.AboutFace;
import org.usfirst.frc.team449.robot.drive.tank.commands.DriveDistance;
import org.usfirst.frc.team449.robot.drive.tank.commands.LineUpShot;
import org.usfirst.frc.team449.robot.drive.tank.commands.ReverseLineUpShot;
import org.usfirst.frc.team449.robot.mechanism.breach.BreachMap;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.SetWedgeChivald;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.SetWedgePortcullis;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.AutoIntakeOut;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.AutoShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * <code>CommandGroup</code> for running the autonomous period
 * <code>Command</code>s
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-02-08
 */
public class Autonomous extends CommandGroup {

	/**
	 * Instantiate a new <code>Autonomous</code>
	 */
	public Autonomous() {
		switch (Robot.strategy) {
		case GET: {
			executeGet();
			break;
		}
		case CROSS: {
			executeCross();
			break;
		}
		case CROSS_RETURN: {
			executeCrossReturn();
			break;
		}
		case CROSS_EJECT_RETURN: {
			executeCrossEjectReturn();
			break;
		}
		case CROSS_PREPARE_SHOT: {
			executeCrossPrepareShot();
			break;
		}
		case CROSS_SHOOT: {
			executeCrossShoot();
			break;
		}
		default: {
			break;
		}
		}
	}

	/**
	 * Find the ball and retrieve it from the middle of the field
	 */
	private void executeGet() {
		// TODO locate ball
		// TODO intake ball
		// TODO turn (optional)
	}

	/**
	 * Cross the defense and stay on the other side
	 */
	private void executeCross() {
		driveToDefense();
		breachDefense();
		addSequential(new DriveDistance(RobotMap.BREATHING_ROOM, true));
	}

	/**
	 * Cross the defense, about face, and drive back, crossing the defense a
	 * second time
	 */
	private void executeCrossReturn() {
		executeCross();
		addSequential(new AboutFace());
		driveBack();
	}

	/**
	 * Cross the defense, about face, eject the ball, and drive back, crossing
	 * the defense a second time
	 */
	private void executeCrossEjectReturn() {
		executeCross();
		addSequential(new AboutFace());
		addSequential(new AutoIntakeOut());
		driveBack();
	}

	/**
	 * Cross the defense, about face, and get ready to shoot at a low goal
	 */
	private void executeCrossPrepareShot() {
		executeCross();
		addSequential(new LineUpShot(Robot.startingPosition));
		// TODO add ultrasonic adjustment
	}

	/**
	 * Cross the defense, about face, and shoot at a low goal
	 */
	private void executeCrossShoot() {
		executeCross();
		addSequential(new AutoShoot());
		addSequential(new ReverseLineUpShot(Robot.startingPosition));
	}

	/**
	 * Drive up to the defense
	 */
	private void driveToDefense() {
		addSequential(new DriveDistance(RobotMap.DISTANCE_TO_DEFENSE, true));
	}

	/**
	 * Return (breaching the defense a second time)
	 */
	private void driveBack() {
		addSequential(new DriveDistance(RobotMap.BREATHING_ROOM, true));
		breachDefense();
		addSequential(new DriveDistance(RobotMap.BREATHING_ROOM));
	}

	/**
	 * Breach the defense
	 */
	private void breachDefense() {
		switch (Robot.defenseType) {
		case PORTCULLIS: {
			addSequential(new SetWedgePortcullis());
			break;
		}
		case CHEVAL_DE_FRISE: {
			addSequential(new SetWedgeChivald());
			break;
		}
		default: {
			break;
		}
		}
		addSequential(new DriveDistance(BreachMap.BREACH_DISTANCE));
	}
}
