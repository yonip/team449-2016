package org.usfirst.frc.team449.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
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
}