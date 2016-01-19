package org.usfirst.frc.team449.robot.drive.tank;

/**
 * a map of constants needed for any form of TankDrive or its subclasses, and not defined higher in the hierarchy
 */
public class TankDriveMap {
    public static final String NAME = "TankDrive";

    public static class Motors {
    	public final int PORT;
    	public final boolean INVERTED;
    	
    	private Motors(int port, boolean inverted) {
    		this.PORT = port;
    		this.INVERTED = inverted;
    	}

    	public static final Motors LEFT1 = new Motors(0, false);
    	public static final Motors LEFT2 = new Motors(1, false);
    	public static final Motors LEFT3 = new Motors(2, true);
    	public static final Motors RIGHT1 = new Motors(3, false);
    	public static final Motors RIGHT2 = new Motors(4, false);
    	public static final Motors RIGHT3 = new Motors(5, true);
    }

    public static class Encoders {
        public static final int LEFT_1_A = -1;
        public static final int LEFT_1_B = -1;
        public static final int LEFT_2_A = -1;
        public static final int LEFT_2_B = -1;
        public static final int LEFT_3_A = -1;
        public static final int LEFT_3_B = -1;
        public static final int RIGHT_1_A = -1;
        public static final int RIGHT_1_B = -1;
        public static final int RIGHT_2_A = -1;
        public static final int RIGHT_2_B = -1;
        public static final int RIGHT_3_A = -1;
        public static final int RIGHT_3_B = -1;
    }

    public static final double P = 0;
    public static final double I = 0;
    public static final double D = 0;

    public static final double SPEED = 1;
}