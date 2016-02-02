package org.usfirst.frc.team449.robot.drive.tank;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

import org.usfirst.frc.team449.robot.drive.DriveSubsystem;
import org.usfirst.frc.team449.robot.drive.tank.commands.DefaultDrive;
import org.usfirst.frc.team449.robot.drive.tank.components.MotorCluster;

/**
 * a Drive subsystem that operates with a tank drive
 */
public class TankDriveSubsystem extends DriveSubsystem {
	private MotorCluster rightCluster;
	private MotorCluster leftCluster;

	public TankDriveSubsystem() {
		System.out.println("Drive init started");

		// initialize motor clusters and add slaves
		this.leftCluster = new MotorCluster(3);
		this.leftCluster.addSlave(makeMotor(TankDriveMap.Motors.LEFT1));
		this.leftCluster.addSlave(makeMotor(TankDriveMap.Motors.LEFT2));
		this.leftCluster.addSlave(makeMotor(TankDriveMap.Motors.LEFT3));

		this.rightCluster = new MotorCluster(3);
		this.rightCluster.addSlave(makeMotor(TankDriveMap.Motors.RIGHT1));
		this.rightCluster.addSlave(makeMotor(TankDriveMap.Motors.RIGHT2));
		this.rightCluster.addSlave(makeMotor(TankDriveMap.Motors.RIGHT3));

		this.leftCluster.setInverted(true);

		System.out.println("Drive init finished");
	}

	public void setThrottle(double left, double right) {
		this.leftCluster.set(left);
		this.rightCluster.set(right);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DefaultDrive());
	}

	private SpeedController makeMotor(TankDriveMap.Motors config) {
		SpeedController motor = new VictorSP(config.PORT);
		((MotorCluster) motor).setInverted(config.INVERTED);
		return motor;
	}
}