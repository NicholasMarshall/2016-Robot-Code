package org.usfirst.frc.team5817.commands.drive;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoOrientWheels extends Command {

	private double angle;
	
    public AutoOrientWheels(double angle) {
    	requires(RexRobot.frontLeftSwerveWheel);
        requires(RexRobot.frontRightSwerveWheel);
        requires(RexRobot.rearLeftSwerveWheel);
        requires(RexRobot.rearRightSwerveWheel);
        setInterruptible(false);
        setRunWhenDisabled(false);
        setTimeout(0.2);
        this.angle = angle;
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    		RexRobot.frontLeftSwerveWheel.setSteerDegrees(angle);
    		RexRobot.frontRightSwerveWheel.setSteerDegrees(angle);
    		RexRobot.rearLeftSwerveWheel.setSteerDegrees(angle);
    		RexRobot.rearRightSwerveWheel.setSteerDegrees(angle);
    }

    protected boolean isFinished() {
    	return isTimedOut();
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	
    }
}
