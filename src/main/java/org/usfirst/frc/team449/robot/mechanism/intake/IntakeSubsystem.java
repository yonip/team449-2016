package org.usfirst.frc.team449.robot.mechanism.intake;

import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.mechanism.MechanismSubsystem;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * a class for intake using a single motor (probably connected to a roller)
 */
public class IntakeSubsystem extends MechanismSubsystem {
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

	public IntakeSubsystem(RobotMap map) {
		super(map);
		System.out.println("Intake init started");

		if (!(map instanceof IntakeMap)) {
			System.err.println("Intake has a map of class " + map.getClass().getSimpleName() + " and not IntakeMap");
		}
		
		IntakeMap intakeMap = (IntakeMap) map;

		this.mainMotor = new VictorSP(intakeMap.motor.PORT);
		this.mainMotor.setInverted(intakeMap.motor.INVERTED);
		
		solenoid = new DoubleSolenoid(intakeMap.solenoid.forward, intakeMap.solenoid.reverse);
		irSensor = new AnalogInput(intakeMap.irSensor.PORT);
		
		System.out.println("Intake init finished");
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
		IntakeMap intakeMap = (IntakeMap) map;
		return irSensor.getVoltage() * intakeMap.irSensor.SCALE_FACTOR;
	}

	public boolean getCloseEnough() {
		IntakeMap intakeMap = (IntakeMap) map;
		return getBallDistance() <= intakeMap.IN_CLOSE_ENOUGH;
	}

	@Override
	public void initDefaultCommand() {
	}
}
