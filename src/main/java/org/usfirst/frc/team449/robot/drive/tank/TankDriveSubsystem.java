package org.usfirst.frc.team449.robot.drive.tank;

import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.components.PIDVelocityMotor;
import org.usfirst.frc.team449.robot.drive.DriveSubsystem;
import org.usfirst.frc.team449.robot.drive.tank.commands.DefaultDrive;
import org.usfirst.frc.team449.robot.drive.tank.components.MotorCluster;
import org.usfirst.frc.team449.robot.drive.tank.components.PIDAngleController;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * a Drive subsystem that operates with a tank drive
 */
public class TankDriveSubsystem extends DriveSubsystem {
	private PIDVelocityMotor rightCluster;
	private PIDVelocityMotor leftCluster;
	private PIDAngleController angleController;
	private AHRS gyro;

	public TankDriveSubsystem(RobotMap map) {
		super(map);
		System.out.println("Drive init started");
		if (!(map instanceof TankDriveMap)) {
			System.err.println("TankDrive has a map of class "
					+ map.getClass().getSimpleName() + " and not TankDriveMap");
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
		enc = new Encoder(tankMap.leftCluster.encoder.a,
				tankMap.leftCluster.encoder.b);
		enc.setDistancePerPulse(tankMap.leftCluster.encoder.dpp);
		this.leftCluster = new PIDVelocityMotor(tankMap.leftCluster.p,
				tankMap.leftCluster.i, tankMap.leftCluster.d, mc, enc, "left");
		this.leftCluster.setOutputRange(-tankMap.leftCluster.outputRange,
				tankMap.leftCluster.outputRange);
		this.leftCluster.setInputRange(-tankMap.SPEED, tankMap.SPEED);
		this.leftCluster
				.setPercentTolerance(tankMap.leftCluster.percentTolerance);
		this.leftCluster.setZeroTolerance(tankMap.leftCluster.zeroTolerance);
		this.leftCluster.enable();
		// right pid
		mc = new MotorCluster(tankMap.rightCluster.cluster.motors.length);
		for (int i = 0; i < tankMap.rightCluster.cluster.motors.length; i++) {
			motor = new VictorSP(tankMap.rightCluster.cluster.motors[i].PORT);
			motor.setInverted(tankMap.rightCluster.cluster.motors[i].INVERTED);
			mc.addSlave(motor);
		}
		mc.setInverted(tankMap.rightCluster.cluster.INVERTED);
		enc = new Encoder(tankMap.rightCluster.encoder.a,
				tankMap.rightCluster.encoder.b);
		enc.setDistancePerPulse(tankMap.rightCluster.encoder.dpp);
		this.rightCluster = new PIDVelocityMotor(tankMap.rightCluster.p,
				tankMap.rightCluster.i, tankMap.rightCluster.d, mc, enc,
				"right");
		this.rightCluster.setOutputRange(-tankMap.rightCluster.outputRange,
				tankMap.rightCluster.outputRange);
		this.rightCluster.setInputRange(-tankMap.SPEED, tankMap.SPEED);
		this.rightCluster
				.setPercentTolerance(tankMap.rightCluster.percentTolerance);
		this.rightCluster.setZeroTolerance(tankMap.rightCluster.zeroTolerance);
		this.rightCluster.enable();

		gyro = new AHRS(SPI.Port.kMXP);
		angleController = new PIDAngleController(tankMap.anglePID.p,
				tankMap.anglePID.i, tankMap.anglePID.d, leftCluster,
				rightCluster, gyro);
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
		this.leftCluster.setSetpoint(left);
		this.rightCluster.setSetpoint(right);
	}

	/**
	 * sets the angle controller to go to theta
	 * 
	 * @param theta
	 *            the angle to turn in place to
	 */
	public void setTurnToAngle(double theta) {
		this.angleController.setSetpoint(theta);
	}

	/**
	 * get if the <code>AngleController</code> has reached the angle it is set
	 * to
	 * 
	 * @return if the <code>AngleController</code> has reached the angle it is
	 *         set to
	 */
	public boolean getTurnAngleDone() {
		return this.angleController.onTarget();
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
