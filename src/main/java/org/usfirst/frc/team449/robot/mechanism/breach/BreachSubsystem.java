package org.usfirst.frc.team449.robot.mechanism.breach;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import org.json.JSONObject;
import org.usfirst.frc.team449.robot.RobotMap;
import org.usfirst.frc.team449.robot.mechanism.MechanismSybsystem;
import org.usfirst.frc.team449.robot.mechanism.intake.IntakeMap;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class BreachSubsystem extends MechanismSybsystem {

	VictorSP motor;
	DigitalInput limitSwitchUpper;
	DigitalInput limitSwitchLower;

	public BreachSubsystem(RobotMap map) {
        super(map);
        System.out.println("Breacher init started");

        if(!(map instanceof BreachMap)) {
            System.err.println("Breach has a map of class " + map.getClass().getSimpleName() + " and not BreachMap");
        }

        this.motor = new VictorSP(((BreachMap)map).motor.PORT);
        this.motor.setInverted(((BreachMap)map).motor.INVERTED);

        this.limitSwitchUpper = new DigitalInput(((BreachMap)map).upper.PORT);
        this.limitSwitchLower = new DigitalInput(((BreachMap)map).lower.PORT);

        System.out.println("Breacher init finished");
    }
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
}
