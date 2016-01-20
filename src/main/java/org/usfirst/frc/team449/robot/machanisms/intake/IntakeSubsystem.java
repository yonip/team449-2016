package org.usfirst.frc.team449.robot.machanisms.intake;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Created by yonipedersen on 1/16/16.
 */
public class IntakeSubsystem extends Subsystem {
	private SpeedController mainMotor;
	private IntakeMap intakeMap;

	public IntakeSubsystem() {
		this.intakeMap = new IntakeMap();
		this.mainMotor = new VictorSP(intakeMap.motors.main.PORT);
		this.mainMotor.setInverted(intakeMap.motors.main.INVERTED);
	}

	/**
	 * sets the motor for intake to go at the given speed
	 * 
	 * @param speed
	 *            the normalized speed of the motor (between -1 and 1)
	 */
	public void set(double speed) {
		mainMotor.set(speed);
	}

	@Override
	public void initDefaultCommand() {

	}
}
