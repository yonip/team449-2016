package org.usfirst.frc.team449.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * <code>CommandGroup</code> for running the autonomous period
 * <code>Command</code>s
 * 
 * @author Ryan Tse <ryantse100@gmail.com>
 * @since 2016-02-08
 */
public class Autonomous extends CommandGroup {

	private DefenseType defenseType;
	/**
	 * Instantiate a new <code>Autonomous</code>
	 */
	public Autonomous(DefenseType defenseType) {
		this.defenseType = defenseType;
		executeStrategy0();
	}

	private void executeStrategy0() {
		// TODO drive certain distance to the defense
		// TODO if something in front, stop
		// TODO set the breach arm in the right position
		// TODO breach
		// TODO about face
		// TODO eject ball
		// TODO drive a certain distance
		// TODO breach
		// TODO drive a certain distance
	}
}
