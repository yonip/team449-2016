package org.usfirst.frc.team449.robot.components;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * a PID controller to control a motor's velocity through PID via the
 * PIDSubsystem
 */
public class PIDVelocityMotor extends PIDComponent implements SpeedController {
	private SpeedController motor;
	private Encoder encoder;
	private double integratedVelocity = 0;
	private String velName;
	private boolean inverted;
	private double speed;
	/**
	 * This defines the deadband around zero which, when read from
	 * {@link #returnPIDInput()}, will be result in no signal to the motor when
	 * {@link #getSetpoint()} returns 0.
	 * </p>
	 * This is done to avoid wheel jitter at near-zero values, since it is known
	 * that for a stationary robot, in the absence of external forces, 0 signal
	 * to the motor will result in no wheel movement.
	 */
	private double zeroTolerance = 0; // speed at which speed ~= 0

	public PIDVelocityMotor(double p, double i, double d, SpeedController motor, Encoder encoder, String name) {
		super(p, i, d);
		this.motor = motor;
		this.encoder = encoder;
		this.velName = name;
	}

	/**
	 * used by the PIDSubsystem to calculate the output wanted for the setpoint
	 * in this class, this returns the attached encoder's rate via getRate()
	 * 
	 * @return the rate of rotation of the gyro as per the encoder's getRate()
	 *         method
	 * @see Encoder#getRate()
	 */
	@Override
	protected double returnPIDInput() {
		return (inverted ? -encoder.getRate() : encoder.getRate());
	}

	@Override
	public void disable() {
		motor.set(0);
		super.disable();
	}

	@Override
	public void enable() {
		integratedVelocity = 0;
		super.enable();
	}

	/**
	 * This defines the deadband around zero which, when read from
	 * {@link #returnPIDInput()}, will be result in no signal to the motor when
	 * {@link #getSetpoint()} returns 0.
	 * </p>
	 * This is done to avoid wheel jitter at near-zero values, since it is known
	 * that for a stationary robot, in the absence of external forces, 0 signal
	 * to the motor will result in no wheel movement.
	 * 
	 * @param zeroTolerance
	 *            the radius of the deadband around zero
	 */
	public void setZeroTolerance(double zeroTolerance) {
		this.zeroTolerance = zeroTolerance;
	}

	/**
	 * Uses the output decided by the PIDSubsystem This output is actually the
	 * derivative of the voltage (and therefore also of the velocity) so it is
	 * integrated by multiplying by the change in time and adding to a field in
	 * this class
	 * 
	 * @param v
	 *            the output decided by the PIDSubsystem, which is the
	 *            derivative of voltage (and velocity)
	 */
	@Override
	protected void usePIDOutput(double v) {
		this.integratedVelocity += v * 0.020; // mult by delta t
		this.integratedVelocity = Math.max(-1, Math.min(1, this.integratedVelocity));
		if (getSetpoint() == 0 && Math.abs(returnPIDInput()) < zeroTolerance) {
			this.integratedVelocity = 0;
		}
		this.motor.pidWrite(integratedVelocity);
		SmartDashboard.putNumber(velName + " intvel", integratedVelocity);
		SmartDashboard.putNumber(velName + " delv", v);
		SmartDashboard.putNumber(velName + " ztol", zeroTolerance);
		SmartDashboard.putNumber(velName + " setp", getSetpoint());
	}
	
	@Override
	public void setSetpoint(double setpoint) {
		super.setSetpoint(inverted ? -setpoint : setpoint);
	}

	/**
	 * @return whether or not the pid subsystem is enabled
	 */
	public boolean getEnabled() {
		return this.getPIDController().isEnabled();
	}
	
	/**
	 * Sets the setpoint for this
	 * @param v
	 */
	@Override
	public void set(double v) {
		this.setSetpoint(v*speed);
	}

	@Override
	public void pidWrite(double output) {
		this.set(output);
	}

	@Override
	public double get() {
		return this.getSetpoint()/speed;
	}

	@Override
	public void set(double speed, byte syncGroup) {
		set(speed);
	}

	@Override
	public void setInverted(boolean isInverted) {
		this.inverted = isInverted;
	}

	@Override
	public boolean getInverted() {
		return this.inverted;
	}

	@Override
	public void stopMotor() {
		this.set(0);
	}
}
