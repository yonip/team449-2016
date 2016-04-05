package org.usfirst.frc.team449.robot;

import org.json.JSONObject;
import org.usfirst.frc.team449.robot.commands.Auto;
import org.usfirst.frc.team449.robot.commands.AutoDrive;
import org.usfirst.frc.team449.robot.commands.AutoDriveIntakeUp;
import org.usfirst.frc.team449.robot.commands.AutoPortcullis;
import org.usfirst.frc.team449.robot.commands.DefenseType;
import org.usfirst.frc.team449.robot.drive.DriveSubsystem;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveMap;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveSubsystem;
import org.usfirst.frc.team449.robot.mechanism.breach.BreachMap;
import org.usfirst.frc.team449.robot.mechanism.breach.BreachSubsystem;
import org.usfirst.frc.team449.robot.mechanism.intake.IntakeMap;
import org.usfirst.frc.team449.robot.mechanism.intake.IntakeSubsystem;
import org.usfirst.frc.team449.robot.vision.VisionSubsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * the class tying all of the components of the robot together.
 */
public class Robot extends IterativeRobot {

	public static final double DELTAT = 0.020;

	/**
	 * the JSONObject containing the configuration for this robot
	 */
	private static JSONObject cfg;
	/**
	 * reference to this robot's Drive subsystem. Any command that uses this
	 * field will cast it to the Drive implementation it uses
	 */
	public static DriveSubsystem drive;
	/**
	 * reference to this robot's Intake subsystem.
	 */
	public static IntakeSubsystem intake;
	/**
	 * 
	 */
	public static BreachSubsystem breach;
	/**
	 * 
	 */
	public static VisionSubsystem vision;
	/**
	 * reference to this robot's OI (Operator Interface)
	 */
	public static OI oi;

	/**
	 * which obstacle the robot will try to breach during auto
	 */
	public static DefenseType autoDefenseType = DefenseType.PORTCULLIS;

	private Command autonomousCommand;

	SendableChooser autoChooser;

	/**
	 * Robot-wide initialization code should go here.
	 *
	 * Users should override this method for default Robot-wide initialization
	 * which will be called when the robot is first powered on. It will be
	 * called exactly one time.
	 *
	 * Warning: the Driver Station "Robot Code" light and FMS "Robot Ready"
	 * indicators will be off until RobotInit() exits. Code in RobotInit() that
	 * waits for enable will cause the robot to never indicate that the code is
	 * ready, causing the robot to be bypassed in a match.
	 */
	@Override
	public void robotInit() {
		try {
			cfg = MappedSubsystem.readConfig("/home/lvuser/cfg.json");
			drive = new TankDriveSubsystem(new TankDriveMap(cfg));
			intake = new IntakeSubsystem(new IntakeMap(cfg));
			breach = new BreachSubsystem(new BreachMap(cfg));
			vision = new VisionSubsystem();
			oi = new OI(new OIMap(cfg));
			autoChooser = new SendableChooser();
			autoChooser.addDefault("nothing", new Auto());
			autoChooser.addObject("Drive dist", new AutoDrive(190, 4.5));
			autoChooser.addObject("Drive Port", new AutoPortcullis(4.5));
			autoChooser.addObject("Drive Intake Up)", new AutoDriveIntakeUp(
					190, 4.5));
			SmartDashboard.putData("Auto chooser", autoChooser);
		} catch (Exception e) {
			String s = e.getMessage();
			StackTraceElement[] arr = e.getStackTrace();
			for (StackTraceElement el : arr) {
				s += "\n  " + el.toString();
			}
			DriverStation.reportError(s, false);
			System.exit(1);
		}
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void autonomousInit() {
		autonomousCommand = (Command) autoChooser.getSelected();
		if (autonomousCommand != null)
			;
		autonomousCommand.start();
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	@Override
	public void testInit() {

	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
}
