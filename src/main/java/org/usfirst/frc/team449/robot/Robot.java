package org.usfirst.frc.team449.robot;

import java.util.HashMap;
import java.util.Set;

import org.json.JSONObject;
import org.usfirst.frc.team449.robot.commands.Auto;
import org.usfirst.frc.team449.robot.commands.AutoDrive;
import org.usfirst.frc.team449.robot.commands.AutoDriveIntakeUp;
import org.usfirst.frc.team449.robot.commands.AutoLowGoal;
import org.usfirst.frc.team449.robot.commands.AutoMap;
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

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is the class that contains all of the subsystems and
 * components for the 2016 robot.
 */
public class Robot extends IterativeRobot {
	public static final double DELTAT = 0.020;
	
	private HashMap<DigitalInput, Command> autos;

	/**
	 * The JSONObject containing the constants from cfg.json for this robot
	 */
	private static JSONObject cfg;
	
	/**
	 * This is the robot's drive subsystem. Any command that uses this
	 * field will cast it to the <code>DriveSubsystem</code> implementation it uses.
	 */
	public static DriveSubsystem drive;
	
	/**
	 * This is the robot's intake subsystem.
	 */
	public static IntakeSubsystem intake;
	
	/**
	 * This is the robot's breach subsystem.
	 */
	public static BreachSubsystem breach;
	
	/**
	 * This is the robot's vision subsystem.
	 */
	public static VisionSubsystem vision;
	
	/**
	 * This is the robot's Operator Interface (OI)
	 */
	public static OI oi;
	
	/**
	 * This is the robot's  configuration map for autonomous period constants.
	 */
	public static AutoMap autoMap;

	/**
	 * This represents the defense the robot will be crossing during the autonoous
	 * period.
	 */
	public static DefenseType autoDefenseType = DefenseType.PORTCULLIS;

	/**
	 * This is the <code>Command</code> that the robot will execute during the
	 * autonomous period.
	 */
	private Command autonomousCommand;

	/**
	 * This is the pre-game OI autonomous period chooser.
	 */
	SendableChooser autoChooser;

	/**
	 * <p>
	 * This instantiates all of the subsystems and autonomous autonomous selections.
	 * </p>
	 * 
	 * <p>
	 * Autonomous period selections are not done by the <code>SendableChooser</code>, as
	 * FMS dropouts sometimes cause <code>SendableChooser</code>s to fail. Instead, the
	 * autonomous period is chosen from headers placed in free DIO ports shorting 5v pin to
	 * the signal pin.
	 * </p>
	 */
	@Override
	public void robotInit() {
		try {
			// Instantaites subsystems
			cfg = MappedSubsystem.readConfig("/home/lvuser/cfg.json");
			autoMap = new AutoMap(cfg);
			drive = new TankDriveSubsystem(new TankDriveMap(cfg));
			intake = new IntakeSubsystem(new IntakeMap(cfg));
			breach = new BreachSubsystem(new BreachMap(cfg));
			vision = new VisionSubsystem();
			oi = new OI(new OIMap(cfg));
			
			// Instantiates auto cchooser
			autos = new HashMap();
			autos.put(new DigitalInput(4), new Auto());
			autos.put(new DigitalInput(5), new AutoDrive(190, 4.5));
			autos.put(new DigitalInput(6), new AutoPortcullis(4.5));
			autos.put(new DigitalInput(7), new AutoDriveIntakeUp(190, 4.5));
			autos.put(new DigitalInput(8), new AutoLowGoal(4.5));
			autos.put(new DigitalInput(9), new AutoDrive(40, 2.5));
			
			// unused due to FMS dropouts
			//autoChooser = new SendableChooser();
			//autoChooser.addDefault("nothing", new Auto());
			//autoChooser.addObject("Drive dist", new AutoDrive(190, 4.5));
			//autoChooser.addObject("Drive Port", new AutoPortcullis(4.5));
			//autoChooser.addObject("Drive Intake Up", new AutoDriveIntakeUp(190, 4.5));
			//autoChooser.addObject("Lowbar lowgoal score", new AutoLowGoal(4.5));
			//SmartDashboard.putData("Auto chooser", autoChooser);
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

	/**
	 * Get the autonomous command and run it during the autonomous period
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = getAutoCommand();//(Command) autoChooser.getSelected();
		if (autonomousCommand != null)
			;
		autonomousCommand.start();
	}
	
	/**
	 * End the autonomous period and begin the telop period
	 */
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

	/**
	 * Get the autonomous command from the DIO pins and display it on the SmartDashboard.
	 */
	private Command getAutoCommand() {
		Set<DigitalInput> inputs = autos.keySet();
		for (DigitalInput di : inputs) {
			SmartDashboard.putBoolean("DIO " + di.getChannel(), di.get());
			System.out.println("DIO " + di.getChannel() + " " + di.get());
		}
		for (DigitalInput di : inputs) {
			if (!di.get()) {
				return autos.get(di);
			}
		}
		return new Auto();
	}
}
