package org.usfirst.frc.team449.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * the Operator Interface, includes access to all joysticks and any other for of input from the drivers
 */
public class OI {
    private Joystick leftDriveJoystick;
    private Joystick rightDriveJoystick;

    public OI() {
        leftDriveJoystick = new Joystick(OIMap.LEFT_DRIVE_STICK);
        rightDriveJoystick = new Joystick(OIMap.RIGHT_DRIVE_STICK);
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