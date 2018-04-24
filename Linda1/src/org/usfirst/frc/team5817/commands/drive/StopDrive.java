package org.usfirst.frc.team5817.commands.drive;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class StopDrive extends Command {

    public StopDrive() {
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
    	RexRobot.frontLeftSwerveWheel.setDrive(0.0);
    	RexRobot.frontRightSwerveWheel.setDrive(0.0);
    	RexRobot.rearLeftSwerveWheel.setDrive(0.0);
    	RexRobot.rearRightSwerveWheel.setDrive(0.0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	
    }

}