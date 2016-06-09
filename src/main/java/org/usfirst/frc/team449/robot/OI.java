package org.usfirst.frc.team449.robot;

import org.usfirst.frc.team449.robot.drive.tank.commands.DriveStraight;
import org.usfirst.frc.team449.robot.drive.tank.commands.TogglePid;
import org.usfirst.frc.team449.robot.drive.tank.commands.TurnAngle;
import org.usfirst.frc.team449.robot.drive.tank.commands.ZeroGyro;
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
 * This is the Operator Interface which includes all of the buttons, joysticks,
 * and other human input devices used to control the robot.
 */
public class OI {
	private OIMap map;

	private Joystick manualOverrides;

	private Joystick gamecube;
	private Joystick buttonPad;

	private int sign = 1;

	/**
	 * Instantiate a new <code>OI</code>.
	 * 
	 * @param map
	 *            {@link OIMap} that contains the constants for the OI
	 */
	public OI(OIMap map) {
		this.map = map;

		gamecube = new Joystick(map.MAIN_CONTROLLER);
		manualOverrides = new Joystick(map.MANUAL_OVERRIDES);
		buttonPad = new Joystick(map.BUTTON_PAD);

		Button intakeIn = new JoystickButton(gamecube, map.INTAKE_IN);
		Button intakeOut = new JoystickButton(gamecube, map.INTAKE_OUT);
		// Button intakeToggle = new JoystickButton(gamecube, 8);
		Button intakeUp = new JoystickButton(gamecube, map.INTAKE_UP);
		Button intakeDown = new JoystickButton(gamecube, map.INTAKE_DOWN);
		Button breachChival = new JoystickButton(gamecube, map.BREACH_CHIVAL);
		Button breachPortcullis = new JoystickButton(gamecube, map.BREACH_PORTCULLIS);
		Button breachClosePrimary = new JoystickButton(gamecube, map.BREACH_CLOSE_PRIMARY);
		Button breachCloseSecondary = new JoystickButton(manualOverrides, map.BREACH_CLOSE_SECONDARY);
		Button cameraToggle = new JoystickButton(gamecube, map.CAMERA_TOGGLE);
		Button driveStraightVel = new JoystickButton(gamecube, map.DRIVE_STRAIGHT);
		Button ignoreIR = new JoystickButton(manualOverrides, map.IGNORE_IR);
		Button togglePid = new JoystickButton(manualOverrides, map.TOGGLE_PID);

		Button faceFront = new JoystickButton(manualOverrides, map.FACE_FRONT);
		Button faceBack = new JoystickButton(manualOverrides, map.FACE_BACK);
		Button faceGoalLeft = new JoystickButton(manualOverrides, map.FACE_LEFT_GOAL);
		Button faceGoalRight = new JoystickButton(manualOverrides, map.FACE_RIGHT_GOAL);

		Button bpIntakeDown = new JoystickButton(buttonPad, map.BP_INTAKE_DOWN);
		Button bpIntakeUp = new JoystickButton(buttonPad, map.BP_INTAKE_UP);
		Button bpIntakeIn = new JoystickButton(buttonPad, map.BP_INTAKE_IN);
		Button bpIntakeOut = new JoystickButton(buttonPad, map.BP_INTAKE_OUT);
		Button bpBreachChival = new JoystickButton(buttonPad, map.BP_BREACH_CHIVAL);
		Button bpBreachPort = new JoystickButton(buttonPad, map.BP_BREACH_PORTCULLIS);
		Button bpBreachClose = new JoystickButton(buttonPad, map.BP_BREACH_CLOSE);
		Button bpCameraToggle = new JoystickButton(buttonPad, map.BP_CAMERA_TOGGLE);

		Button zeroGyro = new JoystickButton(gamecube, map.ZERO_GYRO);

		intakeIn.toggleWhenPressed(new IntakeIn());
		bpIntakeIn.toggleWhenPressed(new IntakeIn());
		intakeOut.whileHeld(new IntakeOut());
		bpIntakeOut.whileHeld(new IntakeOut());
		// TODO intakeToggle command
		intakeUp.whenPressed(new IntakeUp());
		bpIntakeUp.whenPressed(new IntakeUp());
		intakeDown.whenPressed(new IntakeDown());
		bpIntakeDown.whenPressed(new IntakeDown());

		breachChival.whenPressed(new BreachChivald());
		bpBreachChival.whenPressed(new BreachChivald());
		breachPortcullis.whenPressed(new BreachPortcullis());
		bpBreachPort.whenPressed(new BreachPortcullis());
		breachClosePrimary.whenPressed(new BreachStowed());
		breachCloseSecondary.whenPressed(new BreachStowed());
		bpBreachClose.whenPressed(new BreachStowed());

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
		faceGoalLeft.toggleWhenPressed(new TurnAngle(120));
		faceGoalRight.toggleWhenActive(new TurnAngle(-120));

		zeroGyro.whenPressed(new ZeroGyro());
	}

	/**
	 * @return the throttle of the left motor cluster
	 */
	public double getDriveAxisLeft() {
		double ret = sign * this.gamecube.getRawAxis(map.LEFT_DRIVE_STICK);
		return process(ret);
	}

	/**
	 * @return the throttle of the right motor cluster
	 */
	public double getDriveAxisRight() {
		double ret = sign * this.gamecube.getRawAxis(map.RIGHT_DRIVE_STICK);
		return process(ret);
	}

	/**
	 * @return whether the driver is enabling drive straight mode (driving
	 *         straight based on right stick throttle)
	 */
	public boolean isDriveStraightMode() {
		return this.gamecube.getRawButton(map.DRIVE_STRAIGHT);
	}

	/**
	 * <p>
	 * This is a throttle smoothing function used on all joystick input.
	 * </p>
	 * 
	 * <p>
	 * The smoothed value is calculated as the following
	 * </p>
	 * 
	 * sign * max / (1 - (deadband ^ power)) * (((input * sign) ^ power) -
	 * (deadband ^ power))
	 * 
	 * @param input
	 *            raw throttle value (from controller)
	 * @return smoothed throttle value (to send to motor cluster)
	 */
	public double process(double input) {
		int sign = (input < 0) ? -1 : 1; // get the sign of the input
		input *= sign; // get the absolute value
		// if in the deadband, return 0
		if (input < map.DEADBAND) {
			return 0;
		}
		return sign * (map.MAX_VALUE / (1 - Math.pow(map.DEADBAND, map.POWER)))
				* (Math.pow(input, map.POWER) - Math.pow(map.DEADBAND, map.POWER));
	}

	// TODO figure out why this is here
	public void toggle() {
		// this.sign = -this.sign;
	}
}
