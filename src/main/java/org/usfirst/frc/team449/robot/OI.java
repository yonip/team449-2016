package org.usfirst.frc.team449.robot;

import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachChivald;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachPortcullis;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachStowed;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.IntakeDown;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.IntakeIn;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.IntakeOut;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.IntakeUp;

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
	private double db = 0.01;

    public OI(OIMap map) {
        this.map = map;

		/*leftDriveJoystick = new Joystick(map.LEFT_DRIVE_STICK);
        rightDriveJoystick = new Joystick(map.RIGHT_DRIVE_STICK);
        intakeJoystick = new Joystick(map.INTAKE_JOYSTICK);*/
        
        gamecube = new Joystick(map.INTAKE_JOYSTICK);

        Button intakeIn = new JoystickButton(gamecube, map.INTAKE_IN);
        Button intakeOut = new JoystickButton(gamecube, map.INTAKE_OUT);
        //Button intakeToggle = new JoystickButton(gamecube, 8);
        Button intakeUp = new JoystickButton(gamecube, 8);
        Button intakeDown = new JoystickButton(gamecube, 7);
        Button breachChival = new JoystickButton(gamecube, 1);
        Button breachPortcullis = new JoystickButton(gamecube, 3);
        Button breachClose = new JoystickButton(gamecube, 10);
        Button cameraToggle = new JoystickButton(gamecube, 9); // new
        Button driveStraightVel = new JoystickButton(gamecube, 6); // new
        //Button driveStraightPos = new JoystickButton(gamecube, 5); // new        

        intakeIn.toggleWhenPressed(new IntakeIn());
        intakeOut.whileHeld(new IntakeOut());
        // TODO intakeToggle command
        intakeUp.whenPressed(new IntakeUp());
        intakeDown.whenPressed(new IntakeDown());
        
        breachChival.whenPressed(new BreachChivald());
        breachPortcullis.whenPressed(new BreachPortcullis()); // new
        breachClose.whenPressed(new BreachStowed());
        
//        cameraToggle.whenActive(new );
    }

	public double getDriveAxisLeft() {
		//return this.leftDriveJoystick.getAxis(Joystick.AxisType.kY);
		double ret= -this.gamecube.getRawAxis(map.LEFT_DRIVE_STICK);
		if (Math.abs(ret) < db) {
			return 0;
		}
		return ret;
	}

	public double getDriveAxisRight() {
		//return this.rightDriveJoystick.getAxis(Joystick.AxisType.kY);
		double ret = -this.gamecube.getRawAxis(map.RIGHT_DRIVE_STICK);
		if (Math.abs(ret) < db) {
			return 0;
		}
		return ret;
	}

	public boolean isDriveStraightMode() {
		//return this.leftDriveJoystick.getTrigger() || this.rightDriveJoystick.getTrigger();
		return false;
	}
}
