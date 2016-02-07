package org.usfirst.frc.team449.robot.mechanism.intake;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Created by yonipedersen on 1/16/16.
 */
public class IntakeSubsystem extends Subsystem {
	private SpeedController mainMotor;
	private IntakeMap intakeMap;

	public IntakeSubsystem() {
		try {
		this.intakeMap = new IntakeMap();
		this.mainMotor = new VictorSP(intakeMap.motors.main.PORT);
		this.mainMotor.setInverted(intakeMap.motors.main.INVERTED);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		/*
		 * setDefaultCommand(new Command() {
		 * 
		 * @Override protected boolean isFinished() { // TODO Auto-generated
		 * method stub return false; }
		 * 
		 * @Override protected void interrupted() { // TODO Auto-generated
		 * method stub System.out.println(4); }
		 * 
		 * @Override protected void initialize() { // TODO Auto-generated method
		 * stub System.out.println(1); }
		 * 
		 * @Override protected void execute() { // TODO Auto-generated method
		 * stub System.out.println(2); }
		 * 
		 * @Override protected void end() { // TODO Auto-generated method stub
		 * System.out.println(3); } });
		 */
	}
}
