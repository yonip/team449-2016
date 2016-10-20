package org.usfirst.frc.team449.robot;

import org.usfirst.frc.team449.robot.drive.tank.commands.DriveStraight;
import org.usfirst.frc.team449.robot.drive.tank.commands.TurnAngle;
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
 * This is the Operator Interface which includes all of the buttons, joysticks,
 * and other human input devices used to control the robot.
 */
public class OI {
	private OIMap map;

	private Joystick leftStick;
	private Joystick rightStick;

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

		leftStick = new Joystick(2);
		rightStick = new Joystick(4);
		buttonPad = new Joystick(1);
		
		// Navigational buttons
		Button driveStraight = new JoystickButton(rightStick, 1);
		driveStraight.whileHeld(new DriveStraight());
		Button faceFront = new JoystickButton(rightStick, 3);
		Button faceBack = new JoystickButton(rightStick, 2);
		Button turnRight = new JoystickButton(rightStick, 5);
		Button turnLeft = new JoystickButton(leftStick, 4);
		faceFront.toggleWhenPressed(new TurnAngle(0));
		faceBack.toggleWhenPressed(new TurnAngle(180));
		turnLeft.toggleWhenPressed(new TurnAngle(120));
		turnRight.toggleWhenActive(new TurnAngle(-120));

		
		// Breach buttons
		Button stow = new JoystickButton(buttonPad, 1);
		stow.whenPressed(new BreachStowed());
		Button chivald = new JoystickButton(buttonPad, 3);
		chivald.whenPressed(new BreachChivald());
		Button port = new JoystickButton(buttonPad, 4);
		port.whenPressed(new BreachPortcullis());
		
		// Intake
		Button intakeUp = new JoystickButton(buttonPad, 6);
		intakeUp.whenPressed(new IntakeUp());
		Button intakeDown = new JoystickButton(buttonPad, 7);
		intakeDown.whenPressed(new IntakeDown());
		Button intakeIn = new JoystickButton(buttonPad, 8);
		intakeIn.toggleWhenPressed(new IntakeIn());
		Button intakeOut = new JoystickButton(buttonPad, 9);
		intakeOut.whileHeld(new IntakeOut());
		
		// Camera
		Button toggleCamera = new JoystickButton(buttonPad, 10);

		
		
//		bpIntakeIn.toggleWhenPressed(new IntakeIn());
		
//		bpIntakeOut.whileHeld(new IntakeOut());
		// TODO intakeToggle command
		//intakeUp.whenPressed(new IntakeUp());
		//bpIntakeUp.whenPressed(new IntakeUp());
		//intakeDown.whenPressed(new IntakeDown());
		//bpIntakeDown.whenPressed(new IntakeDown());
//		bpBreachChival.whenPressed(new BreachChivald());
//		bpBreachPort.whenPressed(new BreachPortcullis());
		
//		breachCloseSecondary.whenPressed(new BreachStowed());
//		bpBreachClose.whenPressed(new BreachStowed());

		try {
//			cameraToggle.whenPressed(new ToggleCamera());
		} catch (Exception e) {
			System.out.println("(OI constructor) Cameras done goofed, but everything else is (maybe) functional.");
		}

//		ignoreIR.whenPressed(new ToggleIgnoreIR());
//		togglePid.whenPressed(new TogglePid());

		faceFront.toggleWhenPressed(new TurnAngle(0));
		faceBack.toggleWhenPressed(new TurnAngle(180));
//		faceLeft.toggleWhenPressed(new TurnAngle(120));
//		faceRight.toggleWhenActive(new TurnAngle(-120));

//		zeroGyro.whenPressed(new ZeroGyro());
	}

	/**
	 * @return the throttle of the left motor cluster
	 */
	public double getDriveAxisLeft() {
//		double ret = sign * this.gamecube.getRawAxis(map.LEFT_DRIVE_STICK);
		double ret = sign * this.leftStick.getRawAxis(1);
		return process(ret) / 2;
//		return process(ret);
	}

	/**
	 * @return the throttle of the right motor cluster
	 */
	public double getDriveAxisRight() {
//		double ret = sign * this.gamecube.getRawAxis(map.RIGHT_DRIVE_STICK);
		double ret = sign * this.rightStick.getRawAxis(1);
		return process(ret) / 2;
//		return process(ret);
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
	 * This is a throttle smothing function used on all joystick input.
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
