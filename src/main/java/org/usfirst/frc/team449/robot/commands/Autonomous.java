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
	 * Pass defense and release ball
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
		addSequential(new AutoShoot()); //shoot;
	}
	
	
	/**
	 * Pass defense and breach another defense
	 */
	private void executeStrategy4() {
		executeStrategy1();
		// TODO drive a certain distance
		// TODO breach
		// TODO drive a certain distance
	}
}
