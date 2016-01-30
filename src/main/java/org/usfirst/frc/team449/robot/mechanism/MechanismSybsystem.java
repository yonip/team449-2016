package org.usfirst.frc.team449.robot.mechanism;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team449.robot.MappedSubsystem;
import org.usfirst.frc.team449.robot.RobotMap;

/**
 * Outline class for any mechanisms on the robot
 */
public abstract class MechanismSybsystem extends MappedSubsystem {
    public MechanismSybsystem(RobotMap map) {
        super(map);
    }
}
