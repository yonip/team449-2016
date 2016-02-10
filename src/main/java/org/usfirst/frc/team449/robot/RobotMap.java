package org.usfirst.frc.team449.robot;

/**
 * A chart for the robot
 */
public class RobotMap {
    // Drive
    // motors
    public static final int DRIVE_LEFT_MOTOR_1 = 0;
    public static final boolean DRIVE_LEFT_MOTOR_1_INVERTED = false;
    public static final int DRIVE_LEFT_MOTOR_2 = 1;
    public static final boolean DRIVE_LEFT_MOTOR_2_INVERTED = false;
    public static final int DRIVE_LEFT_MOTOR_3 = 2;
    public static final boolean DRIVE_LEFT_MOTOR_3_INVERTED = true;
    public static final int DRIVE_RIGHT_MOTOR_1 = 3;
    public static final boolean DRIVE_RIGHT_MOTOR_1_INVERTED = false;
    public static final int DRIVE_RIGHT_MOTOR_2 = 4;
    public static final boolean DRIVE_RIGHT_MOTOR_2_INVERTED = false;
    public static final int DRIVE_RIGHT_MOTOR_3 = 5;
    public static final boolean DRIVE_RIGHT_MOTOR_3_INVERTED = true;

    // encoders
    public static final int DRIVE_LEFT_ENCODER_A = 0;
    public static final int DRIVE_LEFT_ENCODER_B = 1;
    public static final double DRIVE_LEFT_ENCODER_DPP = -1;
    public static final int DRIVE_RIGHT_ENCODER_A = 2;
    public static final int DRIVE_RIGHT_ENCODER_B = 3;
    public static final double DRIVE_RIGHT_ENCODER_DPP = -1;

    // pid
    public static final double DRIVE_P = 0;
    public static final double DRIVE_I = 0;
    public static final double DRIVE_D = 0;
    public static final double DRIVE_ZERO_TOL = 5.0;
    public static final double DRIVE_TOL = 20.0; // ie n%, for knowing when the pid is on target
    public static final double DRIVE_SPEED = 200.0;
}
