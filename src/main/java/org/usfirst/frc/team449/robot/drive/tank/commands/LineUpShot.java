package org.usfirst.frc.team449.robot.drive.tank.commands;

import org.usfirst.frc.team449.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LineUpShot extends CommandGroup{
	
	public LineUpShot(int position){
		double distanceFromTop = 4.425*(position + 0.5); //Distance in feet of center of defense to left wall
		
		if(position < 0){
			throw(new IllegalArgumentException("An integer between 0-4 inclusive must be passed into LineUpShot"));
		}
		else if(position < 2){ // Goes for the leftmost goal
			double distanceToGoal = RobotMap.DISTANCE_TO_SIDE_GOALS-RobotMap.DISTANCE_TO_DEFENSE;
			double verticalDistance = (RobotMap.VERTICAL_DISTANCE_TO_LEFT_GOAL-distanceFromTop);
			double secantVal = 1/Math.cos(Math.PI/3);
			
			addSequential(new DriveDistance(distanceToGoal-(Math.tan(Math.PI/6)*verticalDistance)));
			addSequential(new TurnAngle(-Math.PI/3));
			addSequential(new DriveDistance(secantVal*(RobotMap.VERTICAL_DISTANCE_TO_LEFT_GOAL-distanceFromTop)));
			
		}
		else if(position < 4){ //Goes for the center goal
			double distanceToGoal = RobotMap.DISTANCE_TO_FRONT_GOAL-RobotMap.DISTANCE_TO_DEFENSE;
			
			//Calculates the angle that the robot will turn to.
			double angle = Math.atan((RobotMap.VERTICAL_DISTANCE_TO_FRONT_GOAL-distanceFromTop)/
					(distanceToGoal-RobotMap.DEFENSE_RAMP_LENGTH-1)); // -1 because the you want to avoid 
																	  // the turn hitting the ramp
			
			double secantVal = 1/Math.cos(angle);
			
			addSequential(new DriveDistance(RobotMap.DEFENSE_RAMP_LENGTH));
			addSequential(new TurnAngle(-angle));
			addSequential(new DriveDistance(secantVal*(distanceToGoal-RobotMap.DEFENSE_RAMP_LENGTH-1)));
			addSequential(new TurnAngle(angle));
			addSequential(new DriveDistance(1));
			
		}
		else if(position == 4){ //Goes for the rightmost goal
			double distanceToGoal = RobotMap.DISTANCE_TO_SIDE_GOALS-RobotMap.DISTANCE_TO_DEFENSE;
			
			//Note: Negated because the defense is below the goal
			double verticalDistance = -(RobotMap.VERTICAL_DISTANCE_TO_RIGHT_GOAL-distanceFromTop);
			double secantVal = 1/Math.cos(Math.PI/3);
			
			addSequential(new DriveDistance(distanceToGoal-(Math.tan(Math.PI/6)*verticalDistance)));
			addSequential(new TurnAngle(Math.PI/3));
			addSequential(new DriveDistance(secantVal*(RobotMap.VERTICAL_DISTANCE_TO_RIGHT_GOAL-distanceFromTop)));
			
		}
		else{
			throw(new IllegalArgumentException("An integer between 0-4 inclusive must be passed into LineUpShot"));
		}
		
		
	}

}
