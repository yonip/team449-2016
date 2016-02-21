package org.usfirst.frc.team449.robot.vision.commands;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.vision.VisionMap;

import com.ni.vision.NIVision;

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

	/**
	 * Instantiate a ToggleCamera command
	 */
	public DefaultVision() {
		//requires(Robot.vision);
	}


	@Override
	protected void initialize() {
		System.out.println("ToggleCamera init");
		
	}

	@Override
	protected void execute() {
		CameraServer.getInstance().setImage(Robot.vision.getFrame());
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
