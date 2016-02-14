package org.usfirst.frc.team449.robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * The base of all subsystems linked to maps. Holds a RobotMap that will be set by subclasses to their own map
 */
public abstract class MappedSubsystem extends Subsystem {
    public final RobotMap map;

    /**
     * creates a mapped subsystem and sets its map
     * @param map the map of constants relevant to this subsystem
     */
    public MappedSubsystem(RobotMap map) {
        this.map = map;
    }

    /**
     * creates a JSONObject from a <code>.json</code> referenced by the given path
     * @param path the path to the <code>.json</code> from which to create the JSONObject
     * @return the JSONObject creted from the given file, or null if there was an IOException
     */
    public static JSONObject readConfig(String path) {
        JSONObject json = null;
        try {
            json = new JSONObject(new String(Files.readAllBytes((new File(path)).toPath()), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace(); // if this happens, we're fucked
        }
        return json;
    }
}
