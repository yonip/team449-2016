package org.usfirst.frc.team449.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team449.robot.machanisms.intake.commands.IntakeIn;
import org.usfirst.frc.team449.robot.machanisms.intake.commands.IntakeOut;

/**
 * the Operator Interface, includes access to all joysticks and any other for of input from the drivers
 */
public class OI {
    private Joystick leftDriveJoystick;
    private Joystick rightDriveJoystick;

    private Joystick intakeJoystick;

    public OI() {
        leftDriveJoystick = new Joystick(OIMap.LEFT_DRIVE_STICK);
        rightDriveJoystick = new Joystick(OIMap.RIGHT_DRIVE_STICK);
        intakeJoystick = new Joystick(OIMap.INTAKE_JOYSTICK);

        Button intakeTogglePower = new JoystickButton(intakeJoystick, OIMap.INTAKE_TOGGLE_POWER);
        intakeTogglePower.toggleWhenPressed(new IntakeIn());

        Button intakeToggleDirection = new JoystickButton(intakeJoystick, OIMap.INTAKE_TOGGLE_DIRECTION);
        intakeToggleDirection.whileHeld(new IntakeOut());
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