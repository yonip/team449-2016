package org.usfirst.frc.team449.robot;

import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachChivald;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachStowed;
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
	
	private Joystick gamecube;

    public OI(OIMap map) {
        this.map = map;

		/*leftDriveJoystick = new Joystick(map.LEFT_DRIVE_STICK);
        rightDriveJoystick = new Joystick(map.RIGHT_DRIVE_STICK);
        intakeJoystick = new Joystick(map.INTAKE_JOYSTICK);*/
        
        gamecube = new Joystick(map.INTAKE_JOYSTICK);

        Button intakeIn = new JoystickButton(gamecube, map.INTAKE_IN);
        Button intakeOut = new JoystickButton(gamecube, map.INTAKE_OUT);
        Button intakeToggle = new JoystickButton(gamecube, 8);
        Button breachChival = new JoystickButton(gamecube, 1);
        Button breachPortcullis = new JoystickButton(gamecube, 3);
        Button breachClose = new JoystickButton(gamecube, 10);

        intakeIn.toggleWhenPressed(new IntakeIn());
        intakeOut.whileHeld(new IntakeOut());
        
        breachChival.whenPressed(new BreachChivald());
        breachClose.whenPressed(new BreachStowed());
    }

	public double getDriveAxisLeft() {
		//return this.leftDriveJoystick.getAxis(Joystick.AxisType.kY);
		return this.gamecube.getRawAxis(map.LEFT_DRIVE_STICK);
	}

	public double getDriveAxisRight() {
		//return this.rightDriveJoystick.getAxis(Joystick.AxisType.kY);
		return this.gamecube.getRawAxis(map.RIGHT_DRIVE_STICK);
	}

	public boolean isDriveStraightMode() {
		//return this.leftDriveJoystick.getTrigger() || this.rightDriveJoystick.getTrigger();
		return false;
	}
}
