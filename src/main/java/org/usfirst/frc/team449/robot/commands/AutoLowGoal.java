package org.usfirst.frc.team449.robot.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.drive.tank.commands.DriveDistance;
import org.usfirst.frc.team449.robot.drive.tank.commands.TurnAngle;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachPortcullis;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachStowed;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.IntakeOut;

/**
 * <code>CommandGroup</code> running robot during the autonomous period
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-03-11
 *
 */
public class AutoLowGoal extends Auto {

	public AutoLowGoal(double timeout) {
		super();
		addSequential(new BreachPortcullis());
		addSequential(new DriveDistance(Robot.autoMap.AUTO_SHOOT_DISTANCE_1, timeout), timeout);
		addSequential(new BreachStowed());
		addSequential(new StayForTimeout(1), 1);
		addSequential(new TurnAngle(Robot.autoMap.AUTO_SHOOT_TURN_ANGLE), 5);
		addSequential(new DriveDistance(Robot.autoMap.AUTO_SHOOT_DISTANCE_2, 5), 5);
		addSequential(new IntakeOut(), 2);
	}
}
