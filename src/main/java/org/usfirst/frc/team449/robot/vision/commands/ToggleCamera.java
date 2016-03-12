package org.usfirst.frc.team449.robot.vision.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.vision.VisionMap;

import com.ni.vision.NIVision;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command to toggle to the next camera
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-02-01
 *
 */
public class ToggleCamera extends Command {

	/**
	 * Instantiate a ToggleCamera command
	 */
	public ToggleCamera() {
		requires(Robot.vision);
	}

	@Override
	protected void initialize() {
		System.out.println("ToggleCamera init");
		NIVision.IMAQdxStopAcquisition(Robot.vision.sessions[Robot.vision.sessionPtr]); // stop
																						// current
																						// cam
		Robot.vision.sessionPtr = Robot.vision.sessionPtr < VisionMap.CAMERA_NAMES.length - 1 ? Robot.vision.sessionPtr + 1
				: 0; // get the new session pointer
		// start capture from new session
		NIVision.IMAQdxConfigureGrab(Robot.vision.sessions[Robot.vision.sessionPtr]);
		NIVision.IMAQdxStartAcquisition(Robot.vision.sessions[Robot.vision.sessionPtr]);
		Robot.oi.toggle();
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
