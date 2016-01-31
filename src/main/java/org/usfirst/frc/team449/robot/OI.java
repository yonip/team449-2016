package org.usfirst.frc.team449.robot;

import org.usfirst.frc.team449.robot.mechanism.intake.commands.IntakeIn;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.IntakeOut;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * the Operator Interface, includes access to all joysticks and any other for of
 * input from the drivers
 */
public class OI/* vey */ {
	private Joystick leftDriveJoystick;
	private Joystick rightDriveJoystick;

	private Joystick intakeJoystick;

	public OI() {
		leftDriveJoystick = new Joystick(OIMap.LEFT_DRIVE_STICK);
		rightDriveJoystick = new Joystick(OIMap.RIGHT_DRIVE_STICK);
		intakeJoystick = new Joystick(OIMap.INTAKE_JOYSTICK);

		Button intakeIn = new JoystickButton(intakeJoystick, OIMap.INTAKE_IN);
		Button intakeOut = new JoystickButton(intakeJoystick, OIMap.INTAKE_OUT);

		intakeIn.toggleWhenPressed(new IntakeIn());
		intakeOut.whileHeld(new IntakeOut());
	}

	public double getDriveAxisLeft() {
		return this.leftDriveJoystick.getAxis(Joystick.AxisType.kY);
	}

	public double getDriveAxisRight() {
		return this.rightDriveJoystick.getAxis(Joystick.AxisType.kY);
	}

	public boolean isDriveStraightMode() {
		return this.leftDriveJoystick.getTrigger() || this.rightDriveJoystick.getTrigger();
	}
}