package org.usfirst.frc.team449.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

import org.usfirst.frc.team449.robot.drive.DriveSubsystem;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveSubsystem;
import org.usfirst.frc.team449.robot.machanisms.intake.IntakeSubsystem;

/**
 * the class tying all of the components of the robot together.
 */
public class Robot extends IterativeRobot {
    /**
     * reference to this robot's Drive subsystem. Any command that uses this field will cast it to the Drive implementation it uses
     */
    public static final DriveSubsystem drive = new TankDriveSubsystem();
    /**
     *
     */
    public static final IntakeSubsystem intake = new IntakeSubsystem();
    /**
     * reference to this robot's OI (Operator Interface)
     */
    public static final OI oi = new OI();
    
    /**
     * Robot-wide initialization code should go here.
     *
     * Users should override this method for default Robot-wide initialization
     * which will be called when the robot is first powered on. It will be called
     * exactly one time.
     *
     * Warning: the Driver Station "Robot Code" light and FMS "Robot Ready"
     * indicators will be off until RobotInit() exits. Code in RobotInit() that
     * waits for enable will cause the robot to never indicate that the code is
     * ready, causing the robot to be bypassed in a match.
     */
    @Override
    public void robotInit() {
    	
    }
    
    @Override
    public void disabledInit() {
    	
    }
    
    @Override
    public void autonomousInit() {
    	
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
    }
}
