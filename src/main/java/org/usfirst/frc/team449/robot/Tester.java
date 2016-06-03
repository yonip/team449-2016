package org.usfirst.frc.team449.robot;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.json.JSONObject;
import org.usfirst.frc.team449.robot.commands.AutoMap;
import org.usfirst.frc.team449.robot.drive.tank.TankDriveMap;
import org.usfirst.frc.team449.robot.mechanism.breach.BreachMap;
import org.usfirst.frc.team449.robot.mechanism.intake.IntakeMap;

/**
 * This tests cfg.json locally by instantiating all the maps and printing an
 * unescaped and an escaped version of the content of the file.
 */
public class Tester {
	/**
	 * Instantiate all maps and print an unescaped and escaped version of the contents of cfg.json
	 */
	public static void main(String[] args) {
		JSONObject jo = null;
		try {
			jo = new JSONObject(new String(Files.readAllBytes((new File("src/main/resources/cfg.json")).toPath()),
					StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace(); // if this happens, we're fucked
		}
		TankDriveMap tdm = new TankDriveMap(jo);
		IntakeMap im = new IntakeMap(jo);
		BreachMap bm = new BreachMap(jo);
		OIMap oim = new OIMap(jo);
		AutoMap am = new AutoMap(jo);
		String s = jo.toString();
		System.out.println(s);
		System.out.println(s.replaceAll("(?<!\\\\)\"", "\\\\\""));
	}
}
