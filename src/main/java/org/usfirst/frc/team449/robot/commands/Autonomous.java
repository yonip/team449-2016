package org.usfirst.frc.team449.robot.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.drive.tank.commands.AboutFace;
import org.usfirst.frc.team449.robot.drive.tank.commands.DriveDistance;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.AutoIntakeOut;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * <code>CommandGroup</code> for running the autonomous period
 * <code>Command</code>s
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-02-08
 */
public class Autonomous extends CommandGroup {

	private int strategy; //Which strategy to execute

	/**
	 * Instantiate a new <code>Autonomous</code>
	 */
	public Autonomous() {
		executeStrategy0();
	}

	private void executeStrategy0() {
		addSequential(new DriveDistance(RobotMap.DISTANCE_TO_DEFENSE, true)); //Drive to defense
		addSequential(Robot.breachCommand); //Breach the defense selected in driver station
		addSequential(new AboutFace()); //About face
		addSequential(new AutoIntakeOut()); //Eject ball
		// TODO drive a certain distance
		// TODO breach
		// TODO drive a certain distance
	}
}
