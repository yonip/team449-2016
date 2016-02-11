package org.usfirst.frc.team449.robot.drive.tank;


import org.usfirst.frc.team449.robot.components.PIDPositionMotor;
import org.usfirst.frc.team449.robot.drive.DriveSubsystem;
import org.usfirst.frc.team449.robot.drive.tank.commands.DefaultDrive;
import org.usfirst.frc.team449.robot.drive.tank.components.MotorCluster;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * a Drive subsystem that operates with a tank drive
 */
public class TankDriveSubsystem extends DriveSubsystem {
	public PIDPositionMotor rightCluster;
	public PIDPositionMotor leftCluster;

	public TankDriveSubsystem() {
		System.out.println("Drive init started");

		// initialize motor clusters and add slaves]
		MotorCluster mc = new MotorCluster(3);
		mc.addSlave(makeMotor(TankDriveMap.Motors.RIGHT1));
		mc.addSlave(makeMotor(TankDriveMap.Motors.RIGHT2));
		mc.addSlave(makeMotor(TankDriveMap.Motors.RIGHT3));
		Encoder enc = new Encoder(TankDriveMap.Encoders.RIGHT.A,
				TankDriveMap.Encoders.RIGHT.B);
		enc.setDistancePerPulse(TankDriveMap.Encoders.RIGHT.DPP);
		rightCluster = new PIDPositionMotor(TankDriveMap.P, TankDriveMap.I,
				TankDriveMap.D, mc, enc);

		mc = new MotorCluster(3);
		mc.addSlave(makeMotor(TankDriveMap.Motors.LEFT1));
		mc.addSlave(makeMotor(TankDriveMap.Motors.LEFT2));
		mc.addSlave(makeMotor(TankDriveMap.Motors.LEFT3));
		mc.setInverted(true);
		enc = new Encoder(TankDriveMap.Encoders.LEFT.A,
				TankDriveMap.Encoders.LEFT.B);
		enc.setDistancePerPulse(TankDriveMap.Encoders.LEFT.DPP);
		leftCluster = new PIDPositionMotor(TankDriveMap.P, TankDriveMap.I,
				TankDriveMap.D, mc, enc);

		System.out.println("Drive init finished");
	}

	public void setThrottle(double left, double right) {
		this.leftCluster.setSetpoint(left);
		this.rightCluster.setSetpoint(right);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DefaultDrive());
	}

	private SpeedController makeMotor(TankDriveMap.Motors config) {
		SpeedController motor = new VictorSP(config.PORT);
		motor.setInverted(config.INVERTED);
		return motor;
	}
}