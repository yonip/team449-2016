package org.usfirst.frc.team449.robot;

import org.usfirst.frc.team449.robot.commands.DefenseType;
import org.usfirst.frc.team449.robot.commands.StartingPosition;
import org.usfirst.frc.team449.robot.commands.Strategy;
import org.usfirst.frc.team449.robot.drive.DriveSubsystem;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveSubsystem;
import org.usfirst.frc.team449.robot.mechanism.breach.BreachSubsystem;
import org.usfirst.frc.team449.robot.mechanism.intake.IntakeSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * the class tying all of the components of the robot together.
 */
public class Robot extends IterativeRobot {
	/**
	 * The breach command selected
	 */
	public static DefenseType defenseType;

	/**
	 * Chooser for which breach is being used
	 */
	private static SendableChooser defenseTypeChooser;

	/**
	 * The auto strategy routine chosen
	 */
	public static Strategy strategy;

	/**
	 * Chooser for which auto routine is being used
	 */
	private static SendableChooser strategyChooser;

	/**
	 * The robot's starting location
	 */
	public static StartingPosition startingPosition;

	/**
	 * Chooser for where the defense being breached is
	 */
	private static SendableChooser startingPositionChooser;

	/**
	 * Drive <code>Subsystem</code>. Any command that uses this field will cast
	 * it to the <code>DriveSubsystem</code> implementation it uses
	 */
	public static final DriveSubsystem drive = new TankDriveSubsystem();

	/**
	 * Intake <code>Subsystem</code>
	 */
	public static final IntakeSubsystem intake = new IntakeSubsystem();

	/**
	 * Breach wedge arm <code>Subsystem</code>
	 */
	public static final BreachSubsystem breach = new BreachSubsystem();

	/**
	 * reference to this robot's OI (Operator Interface)
	 */
	public static final OI oi = new OI();

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
		// Make a chooser on SmartDashboard so drivers can choose from auto
		// routines; Strategy.CROSS is set as default
		strategyChooser.addObject("Get ball from middle", Strategy.GET);
		strategyChooser.addDefault("Cross defense and stay", Strategy.CROSS);
		strategyChooser.addObject("Cross defense and return", Strategy.CROSS_RETURN);
		strategyChooser.addObject("Cross defense, eject ball, and return", Strategy.CROSS_EJECT_RETURN);
		strategyChooser.addObject("Cross defense and prepare to shoot", Strategy.CROSS_PREPARE_SHOT);
		strategyChooser.addObject("Cross defense and shoot", Strategy.CROSS_SHOOT);
		SmartDashboard.putData("Autonomous routine chooser", strategyChooser);

		// Make a chooser on SmartDashboard so drivers can choose which auto
		// breach to do
		defenseTypeChooser.addObject("Portcullis", DefenseType.PORTCULLIS);
		defenseTypeChooser.addObject("Cheval de Friese", DefenseType.CHEVAL_DE_FRISE);
		defenseTypeChooser.addObject("Moat", DefenseType.MOAT);
		// no ramparts?
		// no drawbridge
		// no sally port
		defenseTypeChooser.addObject("Rock Wall", DefenseType.ROCK_WALL);
		defenseTypeChooser.addObject("Rough Terrain", DefenseType.ROUGH_TERRAIN);
		defenseTypeChooser.addDefault("Low Bar", DefenseType.LOW_BAR);
		SmartDashboard.putData("Autonomous breach-type chooser", defenseTypeChooser);

		// Make a chooser on SmartDashboard for starting position so robot can
		// path to goal properly.
		startingPositionChooser.addDefault("Outer Left Position", StartingPosition.LEFT_OUTER);
		startingPositionChooser.addObject("Inner Left Position", StartingPosition.LEFT_INNER);
		startingPositionChooser.addObject("Center Position", StartingPosition.CENTER);
		startingPositionChooser.addObject("Inner Right Position", StartingPosition.RIGHT_INNER);
		startingPositionChooser.addObject("Outer Right Position", StartingPosition.RIGHT_OUTER);
		SmartDashboard.putData("Autonomous defense location chooser", startingPositionChooser);
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void autonomousInit() {
		// The routine selected by the driver
		strategy = (Strategy) strategyChooser.getSelected();
		// The command selected by the driver
		defenseType = (DefenseType) defenseTypeChooser.getSelected();
		// The defense position selected by the driver
		startingPosition = (StartingPosition) strategyChooser.getSelected();
	}

	@Override
	public void teleopInit() {
	}

	@Override
	public void testInit() {
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		System.out.println("teleop");
	}
}