package org.usfirst.frc.team449.robot.components;


import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * a PID controller to control a motor's velocity through PID via the PIDSubsystem
 */
public class PIDVelocityMotor extends PIDSubsystem {
    private SpeedController motor;
    private Encoder encoder;
    private double integratedVelocity = 0;

    public PIDVelocityMotor(double p, double i, double d, SpeedController motor, Encoder encoder) {
        super(p, i, d);
        this.motor = motor;
        this.encoder = encoder;
    }

    /**
     * used by the PIDSubsystem to calculate the output wanted for the setpoint
     * in this class, this returns the attached encoder's rate via getRate()
     * @return the rate of rotation of the gyro as per the encoder's getRate() method
     * @see Encoder#getRate()
     */
    @Override
    protected double returnPIDInput() {
        return encoder.getRate();
    }

    /**
     * Uses the output decided by the PIDSubsystem
     * This output is actually the derivative of the voltage (and therefore also of the velocity) so it is integrated
     * by multiplying by the change in time and adding to a field in this class
     * @param v the output decided by the PIDSubsystem, which is the derivative of voltage (and velocity)
     */
    @Override
    protected void usePIDOutput(double v) {
        this.integratedVelocity += v*0.020; //updates every 20ms in theory, so multiplying by .02 to integrate
        this.motor.pidWrite(v);
    }

    @Override
    protected void initDefaultCommand() {
    }
}
