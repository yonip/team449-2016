package org.usfirst.frc.team449.robot.components;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * A class designed for PID subsystems not linked to commands and that are parts
 * of more complex subsystems Should be the super class for all PID based
 * classes in components packages
 */
public abstract class PIDComponent extends PIDSubsystem {

	public PIDComponent(String name, double p, double i, double d) {
		super(name, p, i, d);
	}

	public PIDComponent(String name, double p, double i, double d, double f) {
		super(name, p, i, d, f);
	}

	public PIDComponent(String name, double p, double i, double d, double f,
			double period) {
		super(name, p, i, d, f, period);
	}

	public PIDComponent(double p, double i, double d) {
		super(p, i, d);
	}

	public PIDComponent(double p, double i, double d, double period, double f) {
		super(p, i, d, period, f);
	}

	public PIDComponent(double p, double i, double d, double period) {
		super(p, i, d, period);
	}

	@Override
	final protected void initDefaultCommand() {
	}
}
