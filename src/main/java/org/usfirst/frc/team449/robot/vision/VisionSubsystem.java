package org.usfirst.frc.team449.robot.vision;

import org.usfirst.frc.team449.robot.MappedSubsystem;
import org.usfirst.frc.team449.robot.vision.commands.DefaultVision;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This is the subsystem for accessing USB cameras on the robot. It extends
 * <code>Subsystem</code>, not <code>MappedSubsystem</code>.
 * 
 * @see Subsystem
 * @see MappedSubsystem
 */
public class VisionSubsystem extends Subsystem {
	/**
	 * {@link Image} from the the current USB camera
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
		System.out.println("Vision init started");

		try {
			sessions = new int[VisionMap.CAMERA_NAMES.length];
			sessionPtr = 0;
			frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

			for (int i = 0; i < VisionMap.CAMERA_NAMES.length; i++) {
				sessions[i] = NIVision.IMAQdxOpenCamera(VisionMap.CAMERA_NAMES[i],
						NIVision.IMAQdxCameraControlMode.CameraControlModeController);
			}
			// NIVision.IMAQdxEnumItem[] arr =
			// NIVision.IMAQdxEnumerateVideoModes(sessions[sessionPtr]).videoModeArray;
			// NIVision.IMAQdxSetAttributeEnum(sessions[sessionPtr],
			// "AcquisitionAttributes::VideoMode", arr[15]);
			NIVision.IMAQdxStartAcquisition(sessions[sessionPtr]);
			NIVision.IMAQdxConfigureGrab(sessions[sessionPtr]);
		} catch (Exception e) {
			System.out.println(
					"(VisionSubsystem constructor) Cameras done goofed, but everything else is (maybe) functional.");
		}
	}

	/**
	 * @return the next <code>Image</code> from the cameras, switching based on
	 *         session number.
	 */
	public Image getFrame() {
		NIVision.IMAQdxStartAcquisition(sessions[sessionPtr]);
		NIVision.IMAQdxGrab(sessions[sessionPtr], frame, 1);
		return frame;
	}

	@Override
	protected void initDefaultCommand() {
		try {
			setDefaultCommand(new DefaultVision());
		} catch (Exception e) {
			System.out.println("(initDefaultCommand) Cameras done goofed, but everything else is (maybe) functional.");
		}
	}
}
