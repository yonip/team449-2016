package org.usfirst.frc.team449.robot.vision.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.vision.VisionMap;

import com.ni.vision.NIVision;

import edu.wpi.first.wpilibj.command.Command;

/**
 * {@link Command} to toggle the camera feed
 */
public class ToggleCamera extends Command {

	/**
	 * Instantiate a new <code>ToggleCamera</code>.
	 */
	public ToggleCamera() {
//		requires(Robot.vision);
	}

	@Override
	protected void initialize() {
//		System.out.println("ToggleCamera init");

		// Stop current camera
//		NIVision.IMAQdxStopAcquisition(Robot.vision.sessions[Robot.vision.sessionPtr]);

		// Get new session pointer
//		Robot.vision.sessionPtr = Robot.vision.sessionPtr < VisionMap.CAMERA_NAMES.length - 1
//				? Robot.vision.sessionPtr + 1 : 0;

		// Start capture from new session
//		NIVision.IMAQdxConfigureGrab(Robot.vision.sessions[Robot.vision.sessionPtr]);
//		NIVision.IMAQdxStartAcquisition(Robot.vision.sessions[Robot.vision.sessionPtr]);
//		Robot.oi.toggle();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		System.out.println("ToggleCamera end");
	}

	@Override
	protected void interrupted() {
		System.out.println("ToggleCamera interupted");
	}
}
