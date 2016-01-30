package org.usfirst.frc.team449.robot.mechanism.intake;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import org.json.JSONObject;
import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.mechanism.MechanismSybsystem;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * a class for intake using a single motor (probably connected to a roller)
 */
public class IntakeSubsystem extends MechanismSybsystem {
    private SpeedController mainMotor;

    public IntakeSubsystem(RobotMap map) {
        super(map);
        System.out.println("Intake init started");

        if(!(map instanceof IntakeMap)) {
            System.err.println("Intake has a map of class " + map.getClass().getSimpleName() + " and not IntakeMap");
        }

        this.mainMotor = new VictorSP(((IntakeMap)map).motor.PORT);
        this.mainMotor.setInverted(((IntakeMap)map).motor.INVERTED);

        System.out.println("Intake init finished");
    }

    /**
     * sets the motor for intake to go at the given speed
     * @param speed the normalized speed of the motor (between -1 and 1)
     */
    public void set(double speed) {
        mainMotor.set(speed);
    }

    @Override
    public void initDefaultCommand() {
    }
}
