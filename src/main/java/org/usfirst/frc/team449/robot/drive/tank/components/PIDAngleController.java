package org.usfirst.frc.team449.robot.drive.tank.components;

import org.usfirst.frc.team449.robot.components.PIDComponent;
import org.usfirst.frc.team449.robot.components.PIDVelocityMotor;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * a PID controller to control a tank drive's wheels' velocities through PID via
 * the PIDSubsystem in order to turn to a specific angle.
 */
public class PIDAngleController extends PIDComponent {

	private PIDVelocityMotor leftMotor;
	private PIDVelocityMotor rightMotor;
	private AHRS gyro;

	public PIDAngleController(double p, double i, double d, PIDVelocityMotor leftMotor, PIDVelocityMotor rightMotor,
			AHRS gyro) {
		super(p, i, d);
		this.getPIDController().setContinuous(true);
		this.setInputRange(0, 360);
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
		this.gyro = gyro;
		this.setPercentTolerance(2);
	}

	/**
	 * used by the PIDSubsystem to calculate the output wanted for the setpoint
	 * in this class, this returns the attached gyro's angle via getAngle()
	 * 
	 * @return the rate of rotation of the gyro as per the gyro's getAngle()
	 *         method
	 * @see AHRS#getAngle()
	 */
	@Override
	protected double returnPIDInput() {
		// SmartDashboard.putNumber("start - gyro reading", startAngle -
		// gyro.getAngle());
		// SmartDashboard.putNumber("start - gyro reading", -(startAngle -
		// gyro.getAngle()));

		double n = gyro.getAngle();

		while (n < 0) {
			n += 360;
		}

		n %= 360;

		SmartDashboard.putNumber("raw gyro reading", n);

		return n;
	}

	/**
	 * Uses the output decided by the PIDSubsystem This output is the normalized
	 * voltage to the motors, effectively directly proportional to the
	 * derivative of the wheels' position. This drives the right motor to the
	 * opposite of the left motor.
	 * 
	 * @param v
	 *            the output decided by the PIDSubsystem
	 */

	@Override
	protected void usePIDOutput(double output) {
		this.leftMotor.setSetpoint(-output);
		this.rightMotor.setSetpoint(-output);
		SmartDashboard.putNumber("angle sp", -output);
	}
}
