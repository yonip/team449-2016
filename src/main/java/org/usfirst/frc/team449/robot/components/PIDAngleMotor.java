package org.usfirst.frc.team449.robot.components;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class PIDAngleMotor extends PIDCommand {

	private PIDController turnController;
	private PIDOutput output;

	private AHRS gyro;

	private final double angleTolerance = 1; // in degrees
	private final double defaultSpeed = 1;

	private double leftVelocity = 0;
	private double rightVelocity = 0;

	private double rotateToAngle;

	public PIDAngleMotor(double p, double i, double d, AHRS ahrs) {
		super(p, i, d);

		turnController = new PIDController(p, i, d, ahrs, output);
		turnController.setInputRange(-180.0f, 180.0f);
		turnController.setOutputRange(-1.0, 1.0);
		turnController.setAbsoluteTolerance(angleTolerance);
		turnController.setContinuous(true);

		gyro = ahrs;

		rotateToAngle = 0;
	}

	public double[] getLeftRight() {
		double kR = 1; // TODO figure out what this is
		double kH = 1;

		double leftRep = gyro.getYaw() * kR + (rotateToAngle - gyro.getAngle())
				* kH;
		double rightRep = -leftRep;
		double left = defaultSpeed + leftRep;
		double right = defaultSpeed + rightRep;
		double out[] = { left, right };
		return out;
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
