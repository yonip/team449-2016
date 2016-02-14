package org.usfirst.frc.team449.robot.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.drive.tank.commands.AboutFace;
import org.usfirst.frc.team449.robot.drive.tank.commands.DriveDistance;
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
		int routine = Robot.autoRoutineChoice;
		if(routine == 0) executeStrategy0();
		else if(routine == 1) executeStrategy1();
		else if(routine == 2) executeStrategy2();
		else if(routine == 3) executeStrategy3();
		else if(routine == 4) executeStrategy4();
		else if(routine == 5) executeStrategy5();
	}

	/**
	 * Pass defense
	 */
	private void executeStrategy0() {
		addSequential(new DriveDistance(RobotMap.DISTANCE_TO_DEFENSE, true)); //Drive to defense
		addSequential(Robot.breachCommand); //Breach the defense selected in driver station
		addSequential(new DriveDistance(RobotMap.BREATHING_ROOM, true)); //Drive a bit away from the defense
	}

	/**
	 * Pass defense, release ball, and turn around
	 */
	private void executeStrategy1() {
		executeStrategy0(); //Breach
		addSequential(new AutoIntakeOut()); //Eject ball
		addSequential(new AboutFace()); //About face
	}
	
	/**
	 * Pass defense and get in line to shoot (so drivers can easily take over)
	 */
	private void executeStrategy2() {
		executeStrategy0(); //Breach
		// TODO Drive to scoring platform
	}
	
	/**
	 * Pass defense and shoot
	 */
	private void executeStrategy3() {
		executeStrategy2(); //Breach and get in line to shoot
		//TODO maybe drive forward some?
		//TODO use ultrasonic to line up and stuff
		addSequential(new AutoShoot()); //shoot;
	}
	
	/**
	 * Pass defense and breach the defense again (best for portcullis, low bar, moat, wall, and cheval)
	 */
	private void executeStrategy4() {
		executeStrategy1(); //Pass defense, release ball, and turn 180 degrees
		addSequential(new DriveDistance(RobotMap.BREATHING_ROOM, true)); //drive back
		addSequential(Robot.breachCommand); //breach the same defense as previous
		addSequential(new DriveDistance(RobotMap.BREATHING_ROOM)); //drive back
		addSequential(new AboutFace()); //turn back around
	}
	
	/**
	 * Get the ball from the middle
	 */
	private void executeStrategy5() {
		// TODO locate ball
		// TODO intake ball
		// TODO turn (optional)
	}
}
