package org.usfirst.frc.team449.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team449.robot.drive.DriveSubsystem;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveSubsystem;

import java.util.HashMap;
import java.util.Map;

/**
 * the class tying all of the components of the robot together.
 */
public class Robot extends IterativeRobot {
    /**
     * reference to this robot's Drive subsystem. Any command that uses this field will cast it to the Drive implementation it uses
     */
    public static final DriveSubsystem drive = new TankDriveSubsystem();
    /**
     * reference to this robot's OI (Operator Interface)
     */
    public static final OI oi = new OI();
    /**
     * a map storing all the subsystems bar the Drive and OI subsystems, referenced by the name as designated by the subsystem's map's NAME field
     */
    public static final Map<String, Subsystem> subsystems = new HashMap<>();
}