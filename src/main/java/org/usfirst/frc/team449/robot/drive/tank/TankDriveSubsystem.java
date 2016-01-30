package org.usfirst.frc.team449.robot.drive.tank;

import edu.wpi.first.wpilibj.VictorSP;
import org.json.JSONObject;
import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.drive.DriveSubsystem;
import org.usfirst.frc.team449.robot.drive.tank.commands.DefaultDrive;
import org.usfirst.frc.team449.robot.drive.tank.components.MotorCluster;
import org.usfirst.frc.team449.robot.mechanism.intake.IntakeMap;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * a Drive subsystem that operates with a tank drive
 */
public class TankDriveSubsystem extends DriveSubsystem {
    private MotorCluster rightCluster;
    private MotorCluster leftCluster;

    public TankDriveSubsystem(RobotMap map) {
        super(map);
        System.out.println("Drive init started");

        if(!(map instanceof TankDriveMap)) {
            System.err.println("TankDrive has a map of class " + map.getClass().getSimpleName() + " and not TankDriveMap");
        }

        //initialize motor clusters and add slaves
        VictorSP motor;
        this.leftCluster = new MotorCluster(((TankDriveMap)map).leftCluster.cluster.motors.length);
        for (int i = 0; i < ((TankDriveMap)map).leftCluster.cluster.motors.length; i++) {
            motor = new VictorSP(((TankDriveMap)map).leftCluster.cluster.motors[i].PORT);
            motor.setInverted(((TankDriveMap)map).leftCluster.cluster.motors[i].INVERTED);
            this.leftCluster.addSlave(motor);
        }
        this.leftCluster.setInverted(((TankDriveMap)map).leftCluster.cluster.INVERTED);

        this.rightCluster = new MotorCluster(((TankDriveMap)map).rightCluster.cluster.motors.length);
        for (int i = 0; i < ((TankDriveMap)map).rightCluster.cluster.motors.length; i++) {
            motor = new VictorSP(((TankDriveMap)map).rightCluster.cluster.motors[i].PORT);
            motor.setInverted(((TankDriveMap)map).rightCluster.cluster.motors[i].INVERTED);
            this.rightCluster.addSlave(motor);
        }
        this.rightCluster.setInverted(((TankDriveMap)map).rightCluster.cluster.INVERTED);

        System.out.println("Drive init finished");
    }

    /**
     * sets the throttle for the left and right clusters as specified by the paramaters
     * @param left the normalized speed between -1 and 1 for the left cluster
     * @param right the normalized speed between -1 and 1 for the right cluster
     */
    public void setThrottle(double left, double right) {
        this.leftCluster.set(left);
        this.rightCluster.set(right);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DefaultDrive());
    }
}