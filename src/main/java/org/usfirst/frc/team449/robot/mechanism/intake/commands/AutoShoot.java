package org.usfirst.frc.team449.robot.mechanism.intake.commands;

import org.usfirst.frc.team449.robot.drive.tank.commands.DriveDistance;
import org.usfirst.frc.team449.robot.mechanism.intake.IntakeMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoShoot extends CommandGroup{
	
	public AutoShoot(){
		addSequential(new DriveDistance(IntakeMap.RAMP_DIST));
		
		addSequential(new DriveDistance(-IntakeMap.RAMP_DIST));
	}

}
