package org.usfirst.frc.team449.robot.drive.tank;

import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.components.PIDPositionMotor;
import org.usfirst.frc.team449.robot.components.PIDVelocityMotor;
import org.usfirst.frc.team449.robot.drive.DriveSubsystem;
import org.usfirst.frc.team449.robot.drive.tank.commands.DefaultDrive;
import org.usfirst.frc.team449.robot.drive.tank.components.MotorCluster;
import org.usfirst.frc.team449.robot.drive.tank.components.PIDAngleController;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * a Drive subsystem that operates with a tank drive
 */
public class TankDriveSubsystem extends DriveSubsystem {
	private PIDVelocityMotor rightClusterVelocity;
	private PIDVelocityMotor leftClusterVelocity;
	private PIDPositionMotor rightClusterPosition;
	private PIDPositionMotor leftClusterPosition;

	private boolean usingVelocityControl;

	private PIDAngleController angleController;
	private AHRS gyro;
	private boolean pidEnabled;

	public TankDriveSubsystem(RobotMap map) {
		super(map);
		System.out.println("Drive init started");
		if (!(map instanceof TankDriveMap)) {
			System.err.println(
					"TankDrive has a map of class " + map.getClass().getSimpleName() + " and not TankDriveMap");
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
		this.leftClusterVelocity = new PIDVelocityMotor(tankMap.leftCluster.p, tankMap.leftCluster.i,
				tankMap.leftCluster.d, mc, enc, "left");
		this.leftClusterVelocity.setOutputRange(-tankMap.leftCluster.outputRange, tankMap.leftCluster.outputRange);
		this.leftClusterVelocity.setInputRange(-tankMap.SPEED, tankMap.SPEED);
		this.leftClusterVelocity.setPercentTolerance(tankMap.leftCluster.percentTolerance);
		this.leftClusterVelocity.setZeroTolerance(tankMap.leftCluster.zeroTolerance);

		// TODO fix the constants and add them to cfg.json
		this.leftClusterPosition = new PIDPositionMotor(tankMap.leftCluster.p, tankMap.leftCluster.i,
				tankMap.leftCluster.d, mc, enc);
		this.leftClusterPosition.setOutputRange(-tankMap.leftCluster.outputRange, tankMap.leftCluster.outputRange);
		this.leftClusterPosition.setInputRange(-tankMap.SPEED, tankMap.SPEED);
		this.leftClusterPosition.setPercentTolerance(tankMap.leftCluster.percentTolerance);

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
		this.rightClusterVelocity = new PIDVelocityMotor(tankMap.rightCluster.p, tankMap.rightCluster.i,
				tankMap.rightCluster.d, mc, enc, "right");
		this.rightClusterVelocity.setOutputRange(-tankMap.rightCluster.outputRange, tankMap.rightCluster.outputRange);
		this.rightClusterVelocity.setInputRange(-tankMap.SPEED, tankMap.SPEED);
		this.rightClusterVelocity.setPercentTolerance(tankMap.rightCluster.percentTolerance);
		this.rightClusterVelocity.setZeroTolerance(tankMap.rightCluster.zeroTolerance);
		this.rightClusterVelocity.enable();

		// TODO fix the constants and add them to cfg.json
		this.rightClusterPosition = new PIDPositionMotor(tankMap.rightCluster.p, tankMap.rightCluster.i,
				tankMap.rightCluster.d, mc, enc);
		this.rightClusterPosition.setOutputRange(-tankMap.rightCluster.outputRange, tankMap.rightCluster.outputRange);
		this.rightClusterPosition.setInputRange(-tankMap.SPEED, tankMap.SPEED);
		this.rightClusterPosition.setPercentTolerance(tankMap.rightCluster.percentTolerance);
		this.rightClusterPosition.enable();

		gyro = new AHRS(SPI.Port.kMXP);
		angleController = new PIDAngleController(tankMap.anglePID.p, tankMap.anglePID.i, tankMap.anglePID.d,
				leftClusterVelocity, rightClusterVelocity, gyro);

		this.setPidEnabled(true);
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
		if (!usingVelocityControl) {
			enableVelocity();
		}
		this.leftClusterVelocity.setSetpoint(left);
		this.rightClusterVelocity.setSetpoint(right);
	}

	/**
	 * drive the robot a certain distance
	 * 
	 * @param distance
	 *            distance to drive the robot
	 */
	public void driveDistance(double distance) {
		if (usingVelocityControl) {
			enablePosition();
		}
		this.leftClusterPosition.setSetpoint(distance);
		this.rightClusterPosition.setSetpoint(distance);
	}

	/**
	 * @return whether the robot has driven the requested distance
	 */
	public boolean getDriveDistanceDone() {
		return leftClusterPosition.onTarget() && rightClusterPosition.onTarget();
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

	/**
	 * call at beginning and before switching from position control to velocity
	 * control
	 */
	private void enableVelocity() {
		rightClusterVelocity.enable();
		leftClusterVelocity.enable();
		rightClusterPosition.disable();
		leftClusterPosition.disable();
		usingVelocityControl = true;
	}

	/**
	 * call before switching from velocity control to position control
	 */
	private void enablePosition() {
		rightClusterPosition.enable();
		leftClusterPosition.enable();
		rightClusterVelocity.disable();
		leftClusterVelocity.disable();
		usingVelocityControl = false;
	}

	/**
	 * switch whether or not the controls consider PID (in case of encoder
	 * failure)
	 */
	public void togglePID() {
		setPidEnabled(!this.pidEnabled);
	}

	private void setPidEnabled(boolean pidEnabled) {
		this.pidEnabled = pidEnabled;
		if (pidEnabled) {
			this.rightClusterVelocity.enable();
			this.leftClusterVelocity.enable();
		} else {
			this.rightClusterVelocity.disable();
			this.leftClusterVelocity.disable();
		}
		SmartDashboard.putBoolean("Drive PID", pidEnabled);
	}
}
