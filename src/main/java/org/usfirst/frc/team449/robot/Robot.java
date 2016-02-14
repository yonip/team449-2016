package org.usfirst.frc.team449.robot;

import org.usfirst.frc.team449.robot.drive.DriveSubsystem;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveSubsystem;
import org.usfirst.frc.team449.robot.mechanism.breach.BreachSubsystem;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachChivald;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachPortcullis;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachLowBar;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachMoat;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachRoughTerrain;
import org.usfirst.frc.team449.robot.mechanism.breach.commands.BreachWall;
import org.usfirst.frc.team449.robot.mechanism.intake.IntakeSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
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
    public static Command breachCommand;
    /**
     * Chooser for which breach is being used
     */
    private static SendableChooser breachChooser;
    /**
     * The routine number chosen
     */
    public static int autoRoutineChoice;
    /**
     * Chooser for which auto routine is being used
     */
    private static SendableChooser autoChooser;
    /**
     *The location of the defense breached
     *0 is farthest from the secret passage, 4 is closest
     */
    public static int defensePosition;
    /**
     * Chooser for where the defense being breached is
     */
    private static SendableChooser defensePositionChooser;
    
	/**
	 * reference to this robot's Drive subsystem. Any command that uses this
	 * field will cast it to the Drive implementation it uses
	 */
	public static final DriveSubsystem drive = new TankDriveSubsystem();
	/**
	 * super duper intake
	 */
	public static final IntakeSubsystem intake = new IntakeSubsystem();
	/**
	 * 
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
		//Make a chooser on SmartDashboard so drivers can choose from auto routines
		autoChooser.addDefault("Cross Defense", 0);
		autoChooser.addObject("Cross, release ball and get in line to cross another", 1);
		autoChooser.addObject("Cross and get in line to shoot", 2);
		autoChooser.addObject("Cross and shoot", 3);
		autoChooser.addObject("Cross and return", 4);
		autoChooser.addObject("Get ball from middle", 5);
		SmartDashboard.putData("Autonomous routine chooser", autoChooser);
		
    	//Make a chooser on SmartDashboard so drivers can choose which auto breach to do
    	breachChooser.addDefault("Low Bar", new BreachLowBar());
    	breachChooser.addObject("Cheval de Friese", new BreachChivald());
    	breachChooser.addObject("Portcullis", new BreachPortcullis());
    	breachChooser.addObject("Moat", new BreachMoat());
    	breachChooser.addObject("Wall", new BreachWall());
    	breachChooser.addObject("Rough Terrain", new BreachRoughTerrain());
    	SmartDashboard.putData("Autonomous breach-type chooser", breachChooser);
    	
    	//Make a chooser on SmartDashboard for defense position so robot can path to goal properly.
    	defensePositionChooser.addDefault("Left Defense", 0);
    	defensePositionChooser.addObject("Left Middle Defense", 1);
    	defensePositionChooser.addObject("Middle Defense", 2);
    	defensePositionChooser.addObject("Right Middle Defense", 3);
    	defensePositionChooser.addObject("Right Defense", 4);
    	SmartDashboard.putData("Autonomous defense location chooser", defensePositionChooser);
    }


	@Override
	public void disabledInit() {

	}

	@Override
	public void autonomousInit() {
    	//The command selected by drive
    	breachCommand = (Command) breachChooser.getSelected();
    	//The routine selected by drive
    	autoRoutineChoice = (int) autoChooser.getSelected();
    	//The defense position selected by drive
    	defensePosition = (int) autoChooser.getSelected();
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