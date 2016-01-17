package org.usfirst.frc.team449.robot.drive.tank;

/**
 * a map of constants needed for any form of TankDrive or its subclasses, and not defined higher in the hierarchy
 */
public class TankDriveMap {
    public static final String NAME = "TankDrive";

    public static class Motors {
        public static final int LEFT_1 = 0;
        public static final int LEFT_2 = 1;
        public static final int LEFT_3 = 2;
        public static final int RIGHT_1 = 3;
        public static final int RIGHT_2 = 4;
        public static final int RIGHT_3 = 5;
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

    public static final double SPEED = 0;
}