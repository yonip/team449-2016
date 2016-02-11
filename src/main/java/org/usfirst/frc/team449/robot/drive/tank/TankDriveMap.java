package org.usfirst.frc.team449.robot.drive.tank;

import org.usfirst.frc.team449.robot.RobotMap;

/**
 * a map of constants needed for any form of TankDrive or its subclasses, and
 * not defined higher in the hierarchy
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

		public static final Motors LEFT1 = new Motors(
				RobotMap.DRIVE_LEFT_MOTOR_1,
				RobotMap.DRIVE_LEFT_MOTOR_1_INVERTED);
		public static final Motors LEFT2 = new Motors(
				RobotMap.DRIVE_LEFT_MOTOR_2,
				RobotMap.DRIVE_LEFT_MOTOR_2_INVERTED);
		public static final Motors LEFT3 = new Motors(
				RobotMap.DRIVE_LEFT_MOTOR_3,
				RobotMap.DRIVE_LEFT_MOTOR_3_INVERTED);
		public static final Motors RIGHT1 = new Motors(
				RobotMap.DRIVE_RIGHT_MOTOR_1,
				RobotMap.DRIVE_RIGHT_MOTOR_1_INVERTED);
		public static final Motors RIGHT2 = new Motors(
				RobotMap.DRIVE_RIGHT_MOTOR_2,
				RobotMap.DRIVE_RIGHT_MOTOR_2_INVERTED);
		public static final Motors RIGHT3 = new Motors(
				RobotMap.DRIVE_RIGHT_MOTOR_3,
				RobotMap.DRIVE_RIGHT_MOTOR_3_INVERTED);
	}

	public static class Encoders {
		public final int A;
		public final int B;
		public final double DPP;

		private Encoders(int a, int b, double dpp) {
			this.A = a;
			this.B = b;
			this.DPP = dpp;
		}

		public static final Encoders LEFT = new Encoders(
				RobotMap.DRIVE_LEFT_ENCODER_A, RobotMap.DRIVE_LEFT_ENCODER_B,
				RobotMap.DRIVE_LEFT_ENCODER_DPP);
		public static final Encoders RIGHT = new Encoders(
				RobotMap.DRIVE_RIGHT_ENCODER_A, RobotMap.DRIVE_RIGHT_ENCODER_B,
				RobotMap.DRIVE_RIGHT_ENCODER_DPP);
	}

	public static final double P = RobotMap.DRIVE_P;
	public static final double I = RobotMap.DRIVE_I;
	public static final double D = RobotMap.DRIVE_D;
	public static final double ZERO_TOL = RobotMap.DRIVE_ZERO_TOL;
	public static final double TOL = RobotMap.DRIVE_TOL; // n% tolerance

	public static final double SPEED = RobotMap.DRIVE_SPEED;

	/**
	 * Distance from center of robot to the wheels
	 */
	public static final double RADIUS = 1; // inches?
	
	public static final double AUTO_SPEED = 1;
	public static final int DIST_CONV = 1;
}