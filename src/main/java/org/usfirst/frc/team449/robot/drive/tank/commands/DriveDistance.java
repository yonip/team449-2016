package org.usfirst.frc.team449.robot.drive.tank.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveMap;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveSubsystem;

public class DriveDistance extends Command {
	private double distance;
	private int counter;
	private boolean forward = true;
	
	
	public DriveDistance(double distance){
		requires(Robot.drive);
		
		if(distance < 0){
			this.distance = -distance;
			forward = false;
		}
		
	}

	
	protected void initialize() {
		counter = 0;
	}

	@Override
	protected void execute() {
		double leftThrottle = TankDriveMap.AUTO_SPEED;
		double rightThrottle = TankDriveMap.AUTO_SPEED;
		
		if(forward)
			((TankDriveSubsystem) Robot.drive).setThrottle(leftThrottle, rightThrottle);
		else
			((TankDriveSubsystem) Robot.drive).setThrottle(-leftThrottle, -rightThrottle);
		
		counter++;
		
	}

	@Override
	protected boolean isFinished() {
		if(counter*TankDriveMap.DIST_CONV > distance){
			return true;
		}
		return false;
	}

	@Override
	protected void end() {
		
		
	}

	@Override
	protected void interrupted() {
		((TankDriveSubsystem) Robot.drive).setThrottle(0, 0);
		
	}

}
