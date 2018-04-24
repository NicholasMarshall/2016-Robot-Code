package org.usfirst.frc.team5817.commands.drive;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class OrientWheelsForTurn extends Command {
	
    public OrientWheelsForTurn() {
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
    	RexRobot.frontRightSwerveWheel.setSteerDegrees(135.0);
    	RexRobot.rearLeftSwerveWheel.setSteerDegrees(305.0);
    	RexRobot.rearRightSwerveWheel.setSteerDegrees(215.0);
    }

    protected boolean isFinished() {
    	return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	
    }
}
