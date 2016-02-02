package org.usfirst.frc.team449.robot.drive.tank;

import org.json.JSONObject;
import org.usfirst.frc.team449.robot.drive.DriveMap;

/**
 * a map of constants needed for any form of TankDrive or its subclasses, and
 * not defined higher in the hierarchy
 */
public class TankDriveMap extends DriveMap {
	/** the map for the left cluster of the tank drive */
	public final TankDriveMap.ClusterPID leftCluster;

	/** the map for the right cluster of the tank drive */
	public final TankDriveMap.ClusterPID rightCluster;

	/** the speed at which this tank drive should go */
	public final double SPEED;

	/**
	 * creates a new TankDrive Map based on the configuration in the given json
	 * any maps in here are to be shared across all tank drive subsystems
	 * 
	 * @param json
	 *            a JSONObject containing the configuration for the maps in this
	 *            object
	 */
	public TankDriveMap(JSONObject json) {
		super(json);
		String path = this.getPath();
		SPEED = getDouble(path + ".speed", json);
		path += ".components";
		this.leftCluster = new ClusterPID(json, path + ".clusterpids.instances.leftCluster");
		this.rightCluster = new ClusterPID(json, path + ".clusterpids.instances.rightCluster");
	}

	@Override
	public String getPath() {
		return super.getPath() + ".tank";
	}

	/**
	 * a map for a MotorCluster of variable size. the size of the Cluster is
	 * defined by the JSONObject
	 */
	public static class MotorCluster extends MapObject {
		/** an array of maps for the motors in this Cluster */
		public final Motor[] motors;

		/** whether the whole cluster should be inverted */
		public final boolean INVERTED;

		public MotorCluster(JSONObject json, String path) {
			super(json, path);
			this.motors = new Motor[getInt(path + "motors.length", json)];
			for (int i = 0; i < this.motors.length; i++) {
				motors[i] = new Motor(json, path + "motors[" + i + "]");
			}
			this.INVERTED = getBoolean(path + ".inverted", json);
		}
	}

	/**
	 * a map for a PID controller that has a MotorCluster, and a single encoder
	 */
	public static class ClusterPID extends PID {
		/** the MotorCluster contrlled by this PID controller */
		public final MotorCluster cluster;

		/** the Encoder used for control in this PID controller */
		public final Encoder encoder;

		public ClusterPID(JSONObject json, String path) {
			super(json, path);
			this.cluster = new MotorCluster(json, path + ".cluster");
			this.encoder = new Encoder(json, path + ".encoder");
		}
	}
}