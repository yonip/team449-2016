package org.usfirst.frc.team449.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
        URL fileUrl = MappedSubsystem.class.getResource("/" + path);
        if (fileUrl == null) {
            DriverStation.reportWarning("Using cached jsonString for json config", false);
            return new JSONObject(jsonString);
        }
        path = fileUrl.getFile().replaceAll("/", "\\");
        //System.out.println(path);
        //System.out.println(new File(".").getAbsolutePath());
        //System.out.println(new File(path).exists());
        JSONObject json = null;
        try {
            json = new JSONObject(new String(Files.readAllBytes((new File(path)).toPath()), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace(); // if this happens, we're fucked
        }
        return json;
    }

    private static final String jsonString = "{\"components\":{},\"MechanismMap\":{\"IntakeMap\":{\"components\":{\"IN_CLOSE_ENOUGH\":1,\"Motor\":{\"instances\":{\"motor\":{\"PORT\":3,\"INVERTED\":false}}},\"OUTPUT_SPEED\":-1,\"DoubleSolenoid\":{\"instances\":{\"solenoid\":{\"forward\":0,\"reverse\":1}}},\"IRSensor\":{\"instances\":{\"irSensor\":{\"PORT\":0}},\"SCALE_FACTOR\":0},\"INPUT_SPEED\":1}},\"BreachMap\":{\"components\":{\"DoubleSolenoid\":{\"instances\":{\"back\":{\"forward\":4,\"reverse\":5},\"front\":{\"forward\":7,\"reverse\":6}}}}},\"components\":{}},\"DriveMap\":{\"TankDriveMap\":{\"components\":{\"SPEED\":50,\"RADIUS\":12,\"ClusterPID\":{\"p\":0.4,\"outputRange\":50,\"MotorCluster\":{\"Motor[]\":{\"0\":{\"INVERTED\":false},\"1\":{\"INVERTED\":false},\"2\":{\"INVERTED\":false}}},\"percentTolerance\":20,\"d\":0,\"instances\":{\"rightCluster\":{\"MotorCluster\":{\"instances\":{\"cluster\":{\"Motor[]\":{\"instances\":{\"motors\":{\"0\":{\"PORT\":4},\"1\":{\"PORT\":5},\"2\":{\"PORT\":6}}}},\"INVERTED\":true}}},\"Encoder\":{\"instances\":{\"encoder\":{\"a\":2,\"b\":3}}}},\"leftCluster\":{\"MotorCluster\":{\"instances\":{\"cluster\":{\"Motor[]\":{\"instances\":{\"motors\":{\"0\":{\"PORT\":0},\"1\":{\"PORT\":1},\"2\":{\"PORT\":2}}}},\"INVERTED\":true}}},\"Encoder\":{\"instances\":{\"encoder\":{\"a\":0,\"b\":1}}}}},\"Encoder\":{\"dpp\":0.0245436926},\"i\":0,\"zeroTolerance\":5}}},\"components\":{}},\"OIMap\":{\"components\":{\"RIGHT_DRIVE_STICK\":3,\"BREACH_PORTCULLIS\":3,\"DRIVE_STRAIGHT\":7,\"LEFT_DRIVE_STICK\":1,\"INTAKE_DOWN\":7,\"BREACH_CLOSE\":10,\"INTAKE_JOYSTICK\":2,\"BREACH_CHIVAL\":1,\"INTAKE_UP\":8,\"CAMERA_TOGGLE\":9,\"INTAKE_OUT\":4,\"INTAKE_IN\":2}}}\n";
}
