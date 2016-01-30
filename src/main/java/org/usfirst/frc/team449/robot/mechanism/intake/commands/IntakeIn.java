package org.usfirst.frc.team449.robot.mechanism.intake.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team449.robot.Robot;
import org.usfirst.frc.team449.robot.mechanism.intake.IntakeMap;

/**
 * a command to pull a ball into the intake
 */
public class IntakeIn extends Command {

    public IntakeIn() {
        requires(Robot.intake);
    }
    /**
     * The initialize method is called the first time this Command is run after
     * being started.
     */
    @Override
    protected void initialize() {
    	System.out.println("IntakeIn init");
    }

    /**
     * The execute method is called repeatedly until this Command either finishes
     * or is canceled.
     */
    @Override
    protected void execute() {
        Robot.intake.set(((IntakeMap)(Robot.intake.map)).INPUT_SPEED);
    }

    /**
     * Returns whether this command is finished. If it is, then the command will
     * be removed and {@link Command#end() end()} will be called.
     * <p>
     * <p>
     * It may be useful for a team to reference the {@link Command#isTimedOut()
     * isTimedOut()} method for time-sensitive commands.
     * </p>
     * $
     *
     * @return whether this command is finished.
     * @see Command#isTimedOut() isTimedOut()
     */
    @Override
    protected boolean isFinished() {
        return false;
    }

    /**
     * Called when the command ended peacefully. This is where you may want to
     * wrap up loose ends, like shutting off a motor that was being used in the
     * command.
     */
    @Override
    protected void end() {
    	System.out.println("IntakeIn end");
    }

    /**
     * Called when the command ends because somebody called
     * {@link Command#cancel() cancel()} or another command shared the same
     * requirements as this one, and booted it out.
     * <p>
     * <p>
     * This is where you may want to wrap up loose ends, like shutting off a motor
     * that was being used in the command.
     * </p>
     * <p>
     * <p>
     * Generally, it is useful to simply call the {@link Command#end() end()}
     * method within this method
     * </p>
     */
    @Override
    protected void interrupted() {
    	System.out.println("ItakeIn interrupted");
    }
}
