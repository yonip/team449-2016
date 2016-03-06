package org.usfirst.frc.team449.robot;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.json.JSONObject;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveMap;
import org.usfirst.frc.team449.robot.mechanism.breach.BreachMap;
import org.usfirst.frc.team449.robot.mechanism.intake.IntakeMap;

/**
 * tests the json on the computer by instantiating all the maps. prints and
 * unescaped and an escaped version of the current json in case it is needed to
 * get config on the roboRIO via {@link MappedSubsystem#jsonString}
 */
public class Tester {

	public static void main(String[] args) {
		JSONObject jo = null;
		try {
			jo = new JSONObject(new String(Files.readAllBytes((new File(
					"src/main/resources/cfg.json")).toPath()),
					StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace(); // if this happens, we're fucked
		}
		TankDriveMap tdm = new TankDriveMap(jo);
		IntakeMap im = new IntakeMap(jo);
		BreachMap bm = new BreachMap(jo);
		OIMap oim = new OIMap(jo);
		String s = jo.toString();
		System.out.println(s);
		System.out.println(s.replaceAll("(?<!\\\\)\"", "\\\\\""));
	}
}
