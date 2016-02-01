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

	/**
	 * Sets the current camera
	 */
	private void setCamSession() {
		NIVision.IMAQdxStopAcquisition(Robot.vision.sessions[Robot.vision.sessionPtr]);
		NIVision.IMAQdxConfigureGrab(Robot.vision.sessions[Robot.vision.sessionPtr]);
		NIVision.IMAQdxStartAcquisition(Robot.vision.sessions[Robot.vision.sessionPtr]);
	}

	@Override
	protected void initialize() {
		System.out.println("ToggleCamera init");
		Robot.vision.sessionPtr = Robot.vision.sessionPtr < VisionMap.NUMBER_OF_CAMERAS - 1
				? Robot.vision.sessionPtr + 1 : 0;
		setCamSession();
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
