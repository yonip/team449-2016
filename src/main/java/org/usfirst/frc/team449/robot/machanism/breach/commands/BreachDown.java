import edu.wpi.first.wpilibj.command.Command;

/**
 * Command to lower the breach arm to the "down" position
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-01-20
 * 
 */
public class BreachDown extends Command {
	
	/**
	 * Instantiate a new <code>BreachDown</code>
	 */
	public BreachDown() {
		requires(Robot.breach)
	}

	protected void initialize() {
		System.out.println("BreachDown init");
	}

	protected void execute() {
		Robot.breach.set(-BreachSubsystem.MOTOR_SPEED_DOWN);
	}

	protected boolean isFinished() {
		return Robot.breach.getLimitSwitchLower();
	}

	protected void end() {
		System.out.println("BreachDown end");
	}

	protected void interrupted() {
		System.out.println("BreachDown interupted");
	}
}
	
}
