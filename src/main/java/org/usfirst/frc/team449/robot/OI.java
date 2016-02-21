package org.usfirst.frc.team449.robot;

import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachChivald;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachPortcullis;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachStowed;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.IntakeDown;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.IntakeIn;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.IntakeOut;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.IntakeUp;
import org.usfirst.frc.team449.robot.vision.commands.ToggleCamera;

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
	private double db = 0.02;

    public OI(OIMap map) {
        this.map = map;

		/*leftDriveJoystick = new Joystick(map.LEFT_DRIVE_STICK);
        rightDriveJoystick = new Joystick(map.RIGHT_DRIVE_STICK);
        intakeJoystick = new Joystick(map.INTAKE_JOYSTICK);*/
        
        gamecube = new Joystick(map.INTAKE_JOYSTICK);

        Button intakeIn = new JoystickButton(gamecube, map.INTAKE_IN);
        Button intakeOut = new JoystickButton(gamecube, map.INTAKE_OUT);
        //Button intakeToggle = new JoystickButton(gamecube, 8);
        Button intakeUp = new JoystickButton(gamecube, map.INTAKE_UP);
        Button intakeDown = new JoystickButton(gamecube, map.INTAKE_DOWN);
        Button breachChival = new JoystickButton(gamecube, map.BREACH_CHIVAL);
        Button breachPortcullis = new JoystickButton(gamecube, map.BREACH_PORTCULLIS);
        Button breachClose = new JoystickButton(gamecube, map.BREACH_CLOSE);
        Button cameraToggle = new JoystickButton(gamecube, map.CAMERA_TOGGLE);
        Button driveStraightVel = new JoystickButton(gamecube, map.DRIVE_STRAIGHT);
        
        intakeIn.toggleWhenPressed(new IntakeIn());
        intakeOut.whileHeld(new IntakeOut());
        // TODO intakeToggle command
        intakeUp.whenPressed(new IntakeUp());
        intakeDown.whenPressed(new IntakeDown());
        
        breachChival.whenPressed(new BreachChivald());
        breachPortcullis.whenPressed(new BreachPortcullis()); // new
        breachClose.whenPressed(new BreachStowed());
        
        //cameraToggle.whenPressed(new ToggleCamera());
    }

	public double getDriveAxisLeft() {
		//return this.leftDriveJoystick.getAxis(Joystick.AxisType.kY);
		double ret= this.gamecube.getRawAxis(map.LEFT_DRIVE_STICK);
		if (Math.abs(ret) < db) {
			return 0;
		}
		
		return quad(ret);
	}

	public double getDriveAxisRight() {
		//return this.rightDriveJoystick.getAxis(Joystick.AxisType.kY);
		double ret = -this.gamecube.getRawAxis(map.RIGHT_DRIVE_STICK);
		if (Math.abs(ret) < db) {
			return 0;
		}
		return quad(ret);
	}

	public boolean isDriveStraightMode() {
		return this.gamecube.getRawButton(map.DRIVE_STRAIGHT);
	}
	
	private static double quad(double inp) {
		int sign = (inp < 0) ? -1 : 1;
		inp = sign * inp;
		return sign * inp * inp * inp;
	}
}
