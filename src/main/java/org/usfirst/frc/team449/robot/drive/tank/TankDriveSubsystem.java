package org.usfirst.frc.team449.robot.drive.tank;

import edu.wpi.first.wpilibj.VictorSP;
import org.usfirst.frc.team449.robot.drive.DriveSubsystem;
import org.usfirst.frc.team449.robot.drive.tank.commands.DefaultDrive;
import org.usfirst.frc.team449.robot.drive.tank.components.MotorCluster;

/**
 * a Drive subsystem that operates with a tank drive
 */
public class TankDriveSubsystem extends DriveSubsystem {
    private MotorCluster rightCluster;
    private MotorCluster leftCluster;

    public TankDriveSubsystem() {
        System.out.println("Drive init started");

        //initialize motor clusters and add slaves
        this.leftCluster = new MotorCluster(2);
        this.leftCluster.addSlave(new VictorSP(TankDriveMap.Motors.LEFT_1));
        this.leftCluster.addSlave(new VictorSP(TankDriveMap.Motors.LEFT_2));

        this.rightCluster = new MotorCluster(2);
        this.rightCluster.addSlave(new VictorSP(TankDriveMap.Motors.RIGHT_1)); 	//first motor
        this.rightCluster.addSlave(new VictorSP(TankDriveMap.Motors.RIGHT_2));

        this.rightCluster.setInverted(true);

        System.out.println("Drive init finished");
    }

    public void setThrottle(double left, double right) {
        this.leftCluster.set(left);
        this.rightCluster.set(right);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DefaultDrive());
    }
}