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
public class OI/*vey*/ {
    private OIMap map;

    private Joystick leftDriveJoystick;
    private Joystick rightDriveJoystick;
	private Joystick intakeJoystick;

    public OI(OIMap map) {
        this.map = map;

		leftDriveJoystick = new Joystick(map.LEFT_DRIVE_STICK);
        rightDriveJoystick = new Joystick(map.RIGHT_DRIVE_STICK);
        intakeJoystick = new Joystick(map.INTAKE_JOYSTICK);

        Button intakeIn = new JoystickButton(intakeJoystick, map.INTAKE_IN);
        Button intakeOut = new JoystickButton(intakeJoystick, map.INTAKE_OUT);
        
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
