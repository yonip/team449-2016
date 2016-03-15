package org.usfirst.frc.team449.robot.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.drive.tank.commands.CrossByPitch;
import org.usfirst.frc.team449.robot.drive.tank.commands.DriveDistance;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachChivald;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachPortcullis;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachStowed;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.IntakeDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * <code>CommandGroup</code> running robot during the autonomous period
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-03-11
 *
 */
public class AutoPortcullis extends Auto {
	public AutoPortcullis(double timeout) {
		super();
		addSequential(new BreachPortcullis());
		addSequential(new DriveDistance(190, timeout));
		addSequential(new BreachStowed());
	}
}
