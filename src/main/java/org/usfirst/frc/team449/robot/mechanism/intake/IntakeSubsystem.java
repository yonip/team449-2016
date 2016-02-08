package org.usfirst.frc.team449.robot.mechanism.intake;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Created by yonipedersen on 1/16/16.
 */
public class IntakeSubsystem extends Subsystem {
	/**
	 * The <code>SpeedController</code> driving the suck in/spit out wheels
	 */
	private SpeedController mainMotor;

	/**
	 * The <code>DoubleSolenoid</code> controlling the piston raising and
	 * lowering the intake subsystem
	 */
	private DoubleSolenoid solenoid;

	/**
	 * The infrared rangefinding sensor's <code>AnalogInput<code> channel
	 */
	private AnalogInput irSensor;

	/**
	 * The constants map
	 */
	private IntakeMap intakeMap;

	public IntakeSubsystem() {
		intakeMap = new IntakeMap();
		solenoid = new DoubleSolenoid(intakeMap.solenoid.FORWARD_PORT, intakeMap.solenoid.REVERSE_PORT);
		mainMotor = new VictorSP(intakeMap.motors.main.PORT);
		mainMotor.setInverted(intakeMap.motors.main.INVERTED);
		irSensor = new AnalogInput(intakeMap.irSensor.PORT);
	}

	/**
	 * sets the motor for intake to go at the given speed
	 * 
	 * @param speed
	 *            the normalized speed of the motor (between -1 and 1)
	 */
	public void setMotorSpeed(double speed) {
		mainMotor.set(speed);
	}

	/**
	 * sets the double solenoid to forward its forward state
	 */
	public void setSolenoidForward() {
		solenoid.set(DoubleSolenoid.Value.kForward);
	}

	/**
	 * sets the double solenoid to forward its reverse state
	 */
	public void setSolenoidReverse() {
		solenoid.set(DoubleSolenoid.Value.kReverse);
	}

	private double getBallDistance() {
		return irSensor.getVoltage() * intakeMap.irSensor.SCALE_FACTOR;
	}

	public boolean getCloseEnough() {
		return getBallDistance() <= IntakeMap.IN_CLOSE_ENOUGH;
	}

	@Override
	public void initDefaultCommand() {
	}
}
