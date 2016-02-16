package org.usfirst.frc.team449.robot.mechanism.breach.commands;

import org.usfirst.frc.team449.robot.drive.tank.commands.DriveDistance;
import org.usfirst.frc.team449.robot.mechanism.breach.BreachMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BreachRoughTerrain extends CommandGroup {
	public BreachRoughTerrain() {
		addSequential(new DriveDistance(BreachMap.BREACH_DISTANCE));
	}
}