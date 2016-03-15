package org.usfirst.frc.team449.robot.vision.commands;

import org.usfirst.frc.team449.robot.Robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Command to toggle to the next camera
 * 
 * @author Gabe Udell <ryantse100@gmail.com>
 * @since 2016-02-06
 *
 */
public class DefaultVision extends Command {

	private boolean cameraFailed = false;

	/**
	 * Instantiate a ToggleCamera command
	 */
	public DefaultVision() {
		requires(Robot.vision);
	}

	@Override
	protected void initialize() {
		// CameraServer.getInstance().setQuality(10);
		System.out.println("ToggleCamera init");
	}

	@Override
	protected void execute() {
		try {
			CameraServer.getInstance().setImage(Robot.vision.getFrame());
		} catch (Exception e) {
			if (!cameraFailed) {
				System.out.println(
						"(DefaultVision execute) Cameras done goofed, but everything else is (maybe) functional.");
				cameraFailed = true;
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
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
