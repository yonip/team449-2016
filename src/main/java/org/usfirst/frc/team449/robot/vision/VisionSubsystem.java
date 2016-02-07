package org.usfirst.frc.team449.robot.vision;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.vision.commands.DefaultVision;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for accessing USB cameras on the robot
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-02-01
 *
 */
public class VisionSubsystem extends Subsystem {
	/**
	 * <code>Image</code> from the the current USB camera
	 */
	public Image frame;

	/**
	 * <code>Array</code> of the cameras' session numbers
	 */
	public int[] sessions;

	/**
	 * Index of the current camera's session number in the <code>sessions</code>
	 * <code>Array</code>
	 */
	public int sessionPtr;

	/**
	 * Instantiate a new <code>VisionSubsystem</code>
	 */
	public VisionSubsystem() {
		try {
			System.out.println("Vision init started");

			sessions = new int[VisionMap.CAMERA_NAMES.length];
			sessionPtr = 0;
			frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

			for (int i = 0; i < VisionMap.CAMERA_NAMES.length; i++) {
				sessions[i] = NIVision
						.IMAQdxOpenCamera(
								VisionMap.CAMERA_NAMES[i],
								NIVision.IMAQdxCameraControlMode.CameraControlModeController);
			}
			NIVision.IMAQdxStartAcquisition(sessions[sessionPtr]);
			NIVision.IMAQdxConfigureGrab(sessions[sessionPtr]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Image getFrame() {
		NIVision.IMAQdxStartAcquisition(sessions[sessionPtr]);
		NIVision.IMAQdxGrab(sessions[sessionPtr], frame, 1);
		return frame;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DefaultVision());
	}
}
