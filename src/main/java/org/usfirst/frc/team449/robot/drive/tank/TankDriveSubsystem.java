package org.usfirst.frc.team449.robot.drive.tank;

import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.components.PIDVelocityMotor;
import org.usfirst.frc.team449.robot.drive.DriveSubsystem;
import org.usfirst.frc.team449.robot.drive.tank.commands.DefaultDrive;
import org.usfirst.frc.team449.robot.drive.tank.components.MotorCluster;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * a Drive subsystem that operates with a tank drive
 */
public class TankDriveSubsystem extends DriveSubsystem {
	private PIDVelocityMotor rightCluster;
	private PIDVelocityMotor leftCluster;

	public TankDriveSubsystem(RobotMap map) {
		super(map);
		System.out.println("Drive init started");
		if (!(map instanceof TankDriveMap)) {
			System.err.println("TankDrive has a map of class " + map.getClass().getSimpleName() + " and not TankDriveMap");
		}

		TankDriveMap tankMap = (TankDriveMap) map;
		// initialize motor clusters and add slaves
		VictorSP motor;
		MotorCluster mc;
		Encoder enc;
		// left pid
		mc = new MotorCluster(tankMap.leftCluster.cluster.motors.length);
		for (int i = 0; i < tankMap.leftCluster.cluster.motors.length; i++) {
			motor = new VictorSP(tankMap.leftCluster.cluster.motors[i].PORT);
			motor.setInverted(tankMap.leftCluster.cluster.motors[i].INVERTED);
			mc.addSlave(motor);
		}
		mc.setInverted(tankMap.leftCluster.cluster.INVERTED);
		enc = new Encoder(tankMap.leftCluster.encoder.a, tankMap.leftCluster.encoder.b);
		enc.setDistancePerPulse(tankMap.leftCluster.encoder.dpp);
		this.leftCluster = new PIDVelocityMotor(tankMap.leftCluster.p, tankMap.leftCluster.i, tankMap.leftCluster.d, mc, enc);
		this.leftCluster.setOutputRange(-1, 1);
		this.leftCluster.setInputRange(-tankMap.SPEED, tankMap.SPEED);
		this.leftCluster.setPercentTolerance(5);
		this.leftCluster.enable();
		// right pid
		mc = new MotorCluster(tankMap.rightCluster.cluster.motors.length);
		for (int i = 0; i < tankMap.rightCluster.cluster.motors.length; i++) {
			motor = new VictorSP(tankMap.rightCluster.cluster.motors[i].PORT);
			motor.setInverted(tankMap.rightCluster.cluster.motors[i].INVERTED);
			mc.addSlave(motor);
		}
		mc.setInverted(tankMap.rightCluster.cluster.INVERTED);
		enc = new Encoder(tankMap.rightCluster.encoder.a, tankMap.rightCluster.encoder.b);
		enc.setDistancePerPulse(tankMap.rightCluster.encoder.dpp);
		this.rightCluster = new PIDVelocityMotor(tankMap.rightCluster.p, tankMap.rightCluster.i, tankMap.rightCluster.d, mc, enc);
		this.rightCluster.setOutputRange(-1, 1);
		this.rightCluster.setInputRange(-tankMap.SPEED, tankMap.SPEED);
		this.rightCluster.setPercentTolerance(5);
		this.rightCluster.enable();
	}

	/**
	 * sets the throttle for the left and right clusters as specified by the
	 * parameters
	 * 
	 * @param left
	 *            the normalized speed between -1 and 1 for the left cluster
	 * @param right
	 *            the normalized speed between -1 and 1 for the right cluster
	 */
	public void setThrottle(double left, double right) {
		//System.out.println(left + " " + right);
		this.leftCluster.setSetpoint(left);
		this.rightCluster.setSetpoint(right);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DefaultDrive());
	}
	
	public void enable() {
		this.rightCluster.enable();
		this.leftCluster.enable();
	}
}
