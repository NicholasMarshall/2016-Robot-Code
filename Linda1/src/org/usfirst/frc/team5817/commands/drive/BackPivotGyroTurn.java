package org.usfirst.frc.team5817.commands.drive;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BackPivotGyroTurn extends Command {
	
	double Kp = 0.0;
	double Ki = 0.0;
	double angle = 0.0;
	
    public BackPivotGyroTurn(double angle) {
    	requires(RexRobot.frontLeftSwerveWheel);
        requires(RexRobot.frontRightSwerveWheel);
        requires(RexRobot.rearLeftSwerveWheel);
        requires(RexRobot.rearRightSwerveWheel);
        setInterruptible(false);
        setRunWhenDisabled(false);
        this.angle = angle;
    }

    protected void initialize() {
    	RexRobot.frontLeftSwerveWheel.setSteerDegrees(63.43);
    	RexRobot.frontRightSwerveWheel.setSteerDegrees(116.57);
    	RexRobot.rearLeftSwerveWheel.setSteerDegrees(0.0);
    	RexRobot.rearRightSwerveWheel.setSteerDegrees(0.0);
    	RexRobot.frontLeftSwerveWheel.setSpeedDriveMode();
    	RexRobot.frontRightSwerveWheel.setSpeedDriveMode();
    	RexRobot.rearLeftSwerveWheel.setSpeedDriveMode();
    	RexRobot.rearRightSwerveWheel.setSpeedDriveMode();
    }

    int counter = 0;
    double totalDelta = 0.0;
    int iterationCounter = 0;
    
    protected void execute() {
    	if(counter == 0) {
            System.out.println("Target: " + this.angle);
        	Kp = 7.0;
        	Ki = 1.0;
    	} else {
	    	double delta = angle - RexRobot.navx.getYaw();
	    	SmartDashboard.putNumber("Error", delta);
	    	System.out.println(delta);
	    	/*
	    	if(Math.abs(delta) <= 2.0) {
		    	totalDelta += delta;
		    	iterationCounter++;
	    	}
	    	double integral = totalDelta / ((iterationCounter == 0) ? 1 : iterationCounter);
	    	double output = (Kp * delta) + (Ki * integral);
	    	if(Math.abs(output) > 400) {
	    		output = 400 * Math.signum(output);
	    	}*/
	    	if(Math.abs(delta) > 15.0) {
	    		double output = 150 * Math.signum(delta);
		    	RexRobot.frontLeftSwerveWheel.setDrive(output);
		    	RexRobot.frontRightSwerveWheel.setDrive(output);
		    	RexRobot.rearLeftSwerveWheel.setDrive(0.0);
		    	RexRobot.rearRightSwerveWheel.setDrive(0.0);
	    	} else if(Math.abs(delta) > 6.5 && Math.abs(delta) < 15.0) {
	    		double output = 75 * Math.signum(delta);
		    	RexRobot.frontLeftSwerveWheel.setDrive(output);
		    	RexRobot.frontRightSwerveWheel.setDrive(output);
		    	RexRobot.rearLeftSwerveWheel.setDrive(0.0);
		    	RexRobot.rearRightSwerveWheel.setDrive(0.0);
	    	} else if(Math.abs(delta) > 0.3 && Math.abs(delta) < 6.5) {
	    		double output = 30 * Math.signum(delta);
	    		RexRobot.frontLeftSwerveWheel.setDrive(output);
		    	RexRobot.frontRightSwerveWheel.setDrive(output);
		    	RexRobot.rearLeftSwerveWheel.setDrive(0.0);
		    	RexRobot.rearRightSwerveWheel.setDrive(0.0);
	    	} else if(Math.abs(delta) < 0.3) {
	    		double output = 0.0;
	        	RexRobot.frontLeftSwerveWheel.setDrive(output);
	        	RexRobot.frontRightSwerveWheel.setDrive(output);
	        	RexRobot.rearLeftSwerveWheel.setDrive(output);
	        	RexRobot.rearRightSwerveWheel.setDrive(output);
	    	}
    	}
    	counter++;
    }

    protected boolean isFinished() {
    	if(counter != 0) {
	    	double delta = angle - RexRobot.navx.getYaw();
	    	boolean answer = (Math.abs(RexRobot.oi.copilotController.getMainY()) > 0.1) || (RexRobot.oi.driveController.getLeftMagnitude() > 0.1) || (RexRobot.oi.driveController.getRightMagnitude() > 0.1) || (Math.abs(delta) < 0.50);
	    	if(answer) {
	    		counter = 0;
	    	}
	    	return answer;
    	}
    	return false;
    }

    protected void end() {
    	double output = 0.0;
    	RexRobot.frontLeftSwerveWheel.setDrive(output);
    	RexRobot.frontRightSwerveWheel.setDrive(output);
    	RexRobot.rearLeftSwerveWheel.setDrive(output);
    	RexRobot.rearRightSwerveWheel.setDrive(output);
    }

    protected void interrupted() {
    	
    }
    
}