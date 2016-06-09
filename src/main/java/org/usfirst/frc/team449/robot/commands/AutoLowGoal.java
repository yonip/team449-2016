package org.usfirst.frc.team449.robot.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.drive.tank.commands.DriveDistance;
import org.usfirst.frc.team449.robot.drive.tank.commands.TurnAngle;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachPortcullis;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachStowed;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.IntakeOut;

import edu.wpi.first.wpilibj.command.Command;

/**
 * <p>
 * Deprecated {@link Command} that inherits from {@link Auto} that was
 * intended to drive the robot up to the low goal and shoot during the
 * autonomous period. This procedure was never used, as the robot side of the
 * robot would crash into the field wall, not allowing the robot to turn all the
 * way around. This caused the robot to miss the autonomous low goals
 * </p>
 * 
 * @deprecated No replacement
 */
@Deprecated
public class AutoLowGoal extends Auto {
	/**
	 * Instantiates a new <code>AutoLowGoal</code>, attempting to drive across
	 * the defense, turn a certain angle, drive up to the goal, and shoot.
	 * 
	 * @param timeout
	 *            how long to drive before aborting the <code>Command</code> for
	 *            safety reasons
	 */
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
