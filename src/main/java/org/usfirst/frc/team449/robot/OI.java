package org.usfirst.frc.team449.robot;

import org.usfirst.frc.team449.robot.drive.tank.commands.DriveStraight;
import org.usfirst.frc.team449.robot.drive.tank.commands.TogglePid;
import org.usfirst.frc.team449.robot.drive.tank.commands.TurnAngle;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachChivald;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachPortcullis;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachStowed;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.IntakeDown;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.IntakeIn;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.IntakeOut;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.IntakeUp;
import org.usfirst.frc.team449.robot.mechanism.intake.commands.ToggleIgnoreIR;
import org.usfirst.frc.team449.robot.vision.commands.ToggleCamera;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * the Operator Interface, includes access to all joysticks and any other for of
 * input from the drivers
 */
public class OI/* vey */ {
	private OIMap map;

	private Joystick leftDriveJoystick;
	private Joystick rightDriveJoystick;
	// private Joystick intakeJoystick;

	private Joystick manualOverrides;

	private Joystick gamecube;
	private double db = 0.02;
	private int sign = 1;

	public OI(OIMap map) {
		this.map = map;

		gamecube = new Joystick(map.MAIN_CONTROLLER);
		manualOverrides = new Joystick(map.MANUAL_OVERRIDES);
		// gamecube = new Joystick(map.INTAKE_JOYSTICK);

		Button intakeIn = new JoystickButton(gamecube, map.INTAKE_IN);
		Button intakeOut = new JoystickButton(gamecube, map.INTAKE_OUT);
		// Button intakeToggle = new JoystickButton(gamecube, 8);
		Button intakeUp = new JoystickButton(gamecube, map.INTAKE_UP);
		Button intakeDown = new JoystickButton(gamecube, map.INTAKE_DOWN);
		Button breachChival = new JoystickButton(gamecube, map.BREACH_CHIVAL);
		Button breachPortcullis = new JoystickButton(gamecube, map.BREACH_PORTCULLIS);
		Button breachClose = new JoystickButton(gamecube, map.BREACH_CLOSE);
		Button cameraToggle = new JoystickButton(gamecube, map.CAMERA_TOGGLE);
		Button driveStraightVel = new JoystickButton(gamecube, map.DRIVE_STRAIGHT);
		Button ignoreIR = new JoystickButton(manualOverrides, map.IGNORE_IR);
		Button togglePid = new JoystickButton(manualOverrides, map.TOGGLE_PID);

		Button faceFront = new JoystickButton(manualOverrides, map.FACE_FRONT);
		Button faceBack = new JoystickButton(manualOverrides, map.FACE_BACK);
		Button faceGoalLeft = new JoystickButton(manualOverrides, map.FACE_LEFT_GOAL);
		Button faceGoalRight = new JoystickButton(manualOverrides, map.FACE_RIGHT_GOAL);

		intakeIn.toggleWhenPressed(new IntakeIn());
		intakeOut.whileHeld(new IntakeOut());
		// TODO intakeToggle command
		intakeUp.whenPressed(new IntakeUp());
		intakeDown.whenPressed(new IntakeDown());

		breachChival.whenPressed(new BreachChivald());
		breachPortcullis.whenPressed(new BreachPortcullis()); // new
		breachClose.whenPressed(new BreachStowed());

		try {
			cameraToggle.whenPressed(new ToggleCamera());
		} catch (Exception e) {
			System.out.println("(OI constructor) Cameras done goofed, but everything else is (maybe) functional.");
		}

		ignoreIR.whenPressed(new ToggleIgnoreIR());
		togglePid.whenPressed(new TogglePid());
		driveStraightVel.whileHeld(new DriveStraight());

		faceFront.toggleWhenPressed(new TurnAngle(0));
		faceBack.toggleWhenPressed(new TurnAngle(180));
		faceGoalLeft.toggleWhenPressed(new TurnAngle(15));
	}

	public double getDriveAxisLeft() {
		// return this.leftDriveJoystick.getAxis(Joystick.AxisType.kY);
		double ret = sign * this.gamecube.getRawAxis(map.LEFT_DRIVE_STICK);

		return process(ret);
	}

	public double getDriveAxisRight() {
		// return this.rightDriveJoystick.getAxis(Joystick.AxisType.kY);
		double ret = sign * this.gamecube.getRawAxis(map.RIGHT_DRIVE_STICK);

		return process(ret);
	}

	public boolean isDriveStraightMode() {
		return this.gamecube.getRawButton(map.DRIVE_STRAIGHT);
	}

	public double process(double input) {
		int sign = (input < 0) ? -1 : 1; // get the sign of the input
		input *= sign; // get the absolute value
		// if in the deadband, return 0
		if (input < map.DEADBAND) {
			return 0;
		}
		return sign * (map.MAX_VALUE / (1 - Math.pow(map.DEADBAND, map.POWER)))
				* (Math.pow(input, map.POWER) - Math.pow(map.DEADBAND, map.POWER));
		/*
		 * sign * max ------------------ * (|input|^n - deadband^power)
		 * 1-(deadband)^power
		 * 
		 * where sign is the sign of the input this makes a smooth joystick
		 * curve, according to szabo
		 */
	}

	public void toggle() {
		// this.sign = -this.sign;
	}
}
