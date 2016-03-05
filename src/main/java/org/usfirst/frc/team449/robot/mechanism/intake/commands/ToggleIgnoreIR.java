package org.usfirst.frc.team449.robot.mechanism.intake.commands;

import org.usfirst.frc.team449.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * "Toggles ignoreIR"
 * If ignoreIR is false, the robot will only stop {@link IntakeIn IntakeIn} when the
 * user presses the button that initialized the command again. If it's true, the
 * command will stop when the IR detects the ball.
 */
public class ToggleIgnoreIR extends Command {

    public ToggleIgnoreIR() {
        requires(Robot.intake);
    }

    protected void initialize() {
    	Robot.intake.toggleIgnoreIR();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
