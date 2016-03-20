package org.usfirst.frc.team449.robot.drive.tank.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveMap;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * <code>Command</code> for driving a certain distance (for use in auto period)
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-03-11
 *
 */
public class DriveDistance extends Command {

	private double distance;

	public DriveDistance(double distance, double timeout) {
		super(timeout);
		requires(Robot.drive);
		this.distance = distance;
	}

	@Override
	protected void initialize() {
		System.out.println("DriveDistance init");
		TankDriveSubsystem drive = (TankDriveSubsystem) (Robot.drive);
		drive.reset();
	}

	@Override
	protected void execute() {
		TankDriveSubsystem drive = (TankDriveSubsystem) (Robot.drive);
		TankDriveMap map = (TankDriveMap) (drive.map);
		drive.setThrottle(-map.leftCluster.speed*.5, -map.rightCluster.speed*.5);
	}

	@Override
	protected boolean isFinished() {
		TankDriveSubsystem drive = (TankDriveSubsystem) (Robot.drive);
		SmartDashboard.putNumber("Distance", drive.getDistance());
		return drive.getDistance() > this.distance;
	}

	@Override
	protected void end() {
		System.out.println("DriveDistance end");
		TankDriveSubsystem drive = (TankDriveSubsystem) (Robot.drive);
		drive.setThrottle(0, 0);
	}

	@Override
	protected void interrupted() {
		System.out.println("DriveDistance interupted");
		TankDriveSubsystem drive = (TankDriveSubsystem) (Robot.drive);
		drive.setThrottle(0, 0);
	}
}
