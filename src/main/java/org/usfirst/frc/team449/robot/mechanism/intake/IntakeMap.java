package org.usfirst.frc.team449.robot.mechanism.intake;

/**
 * Created by yonipedersen on 1/16/16.
 */
public class IntakeMap {
	public final static String NAME = "Intake";
	public final Motors motors;
	public final PistonSolenoid solenoid;
	public final IRChannel irSensor;

	public IntakeMap() {
		motors = new Motors();
		solenoid = new PistonSolenoid();
		irSensor = new IRChannel();
	}

	public class Motors {
		public final Motor main;

		public Motors() {
			this.main = new Motor();
		}

		public class Motor {
			public final int PORT = 6;
			public final boolean INVERTED = false;
		}
	}

	public class Encoders {
		public final int MAIN_A = -1;
		public final int MAIN_B = -1;
	}

	public class PistonSolenoid {
		public final int FORWARD_PORT = 4;
		public final int REVERSE_PORT = 5;
	}

	public class IRChannel {
		public final int PORT = 0;

		// TODO determine the voltage-to-distance scale factor
		/**
		 * The output voltage to actual distance scale factor (inches/Volt)
		 */
		public final double SCALE_FACTOR = 1;
	}

	public static final double OUTPUT_SPEED = 1;
	public static final double INPUT_SPEED = 1;

	// TODO determine how far the ball should be from the US sensor before
	// stopping <code>IntakeIn</code>
	/**
	 * The distance between the infrared sensor and the ball at which
	 * <code>IntakeIn</code> stops
	 */
	public static final double IN_CLOSE_ENOUGH = 1;
}
