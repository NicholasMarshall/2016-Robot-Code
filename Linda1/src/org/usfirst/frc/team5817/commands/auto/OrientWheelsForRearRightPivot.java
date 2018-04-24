package org.usfirst.frc.team5817.commands.auto;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class OrientWheelsForRearRightPivot extends Command {
	
    public OrientWheelsForRearRightPivot() {
    	requires(RexRobot.frontLeftSwerveWheel);
        requires(RexRobot.frontRightSwerveWheel);
        requires(RexRobot.rearLeftSwerveWheel);
        requires(RexRobot.rearRightSwerveWheel);
        setInterruptible(true);
        setRunWhenDisabled(false);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	RexRobot.frontLeftSwerveWheel.setSteerDegrees(45.0);
    	RexRobot.frontRightSwerveWheel.setSteerDegrees(90.0);
    	RexRobot.rearLeftSwerveWheel.setSteerDegrees(0.0);
    	RexRobot.rearRightSwerveWheel.setSteerDegrees(45.0);
    }

    protected boolean isFinished() {
    	return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	
    }
    
}