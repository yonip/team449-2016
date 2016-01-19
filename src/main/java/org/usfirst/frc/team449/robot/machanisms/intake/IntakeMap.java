package org.usfirst.frc.team449.robot.machanisms.intake;

/**
 * Created by yonipedersen on 1/16/16.
 */
public class IntakeMap {
    public final static String NAME = "Intake";
    public final Motors motors;
    
    public IntakeMap() {
    	this.motors = new Motors();
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

    public static final double OUTPUT_SPEED = 1;
    public static final double INPUT_SPEED = 1;
}
