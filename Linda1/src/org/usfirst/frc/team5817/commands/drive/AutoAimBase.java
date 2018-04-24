package org.usfirst.frc.team5817.commands.drive;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoAimBase extends Command {
	
	double Kp = 0.0;
	double Ki = 0.0;
	double angle = 0.0;
	
    public AutoAimBase() {
    	requires(RexRobot.frontLeftSwerveWheel);
        requires(RexRobot.frontRightSwerveWheel);
        requires(RexRobot.rearLeftSwerveWheel);
        requires(RexRobot.rearRightSwerveWheel);
        setInterruptible(false);
        setRunWhenDisabled(false);
    }

    protected void initialize() {
    	RexRobot.frontLeftSwerveWheel.setSteerDegrees(0.0);
    	RexRobot.frontRightSwerveWheel.setSteerDegrees(180.0);
    	RexRobot.rearLeftSwerveWheel.setSteerDegrees(296.57);
    	RexRobot.rearRightSwerveWheel.setSteerDegrees(243.43);
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
    		double centerX = RexRobot.getBestCenterX();
            double centerY = RexRobot.getBestCenterY();
            System.out.println(centerY);
            double distance = (0.0000006 * Math.pow(centerY, 3)) + (0.00008 * Math.pow(centerY, 2)) + (0.0092 * centerY) + 3.9479; //distance = 6E-07x3 + 8E-05x2 + 0.0092x + 3.9479
            this.angle = -RexRobot.getAngleChange(centerX, distance);
            System.out.println("Target: " + this.angle);
            System.out.println(distance);
        	Kp = 8.0;
        	Ki = 5.0;
    	} else {
	    	double delta = angle - RexRobot.navx.getYaw(); //angle - RexRobot.navx.getYaw();
	    	SmartDashboard.putNumber("Error", delta);
	    	if(Math.abs(delta) <= 2.0) {
		    	totalDelta += delta;
		    	iterationCounter++;
	    	}
	    	double integral = totalDelta / ((iterationCounter == 0) ? 1 : iterationCounter);
	    	double output = (Kp * delta) + (Ki * integral);
	    	if(Math.abs(output) > 400) {
	    		output = 400 * Math.signum(output);
	    	}
	    	RexRobot.frontLeftSwerveWheel.setDrive(0.0);
	    	RexRobot.frontRightSwerveWheel.setDrive(0.0);
	    	RexRobot.rearLeftSwerveWheel.setDrive(output);
	    	RexRobot.rearRightSwerveWheel.setDrive(output);
	    	/*if(Math.abs(delta) > 6.5) {
	    		double output = 75 * Math.signum(delta);
		    	RexRobot.frontLeftSwerveWheel.setDrive(0.0);
		    	RexRobot.frontRightSwerveWheel.setDrive(0.0);
		    	RexRobot.rearLeftSwerveWheel.setDrive(output);
		    	RexRobot.rearRightSwerveWheel.setDrive(output);
	    	} else if(Math.abs(delta) > 1.2 && Math.abs(delta) < 6.5) {
	    		double output = 13 * Math.signum(delta);
	    		RexRobot.frontLeftSwerveWheel.setDrive(0.0);
		    	RexRobot.frontRightSwerveWheel.setDrive(0.0);
		    	RexRobot.rearLeftSwerveWheel.setDrive(output);
		    	RexRobot.rearRightSwerveWheel.setDrive(output);
	    	} else if(Math.abs(delta) < 1.2) {
	    		double output = 0.0;
	        	RexRobot.frontLeftSwerveWheel.setDrive(output);
	        	RexRobot.frontRightSwerveWheel.setDrive(output);
	        	RexRobot.rearLeftSwerveWheel.setDrive(output);
	        	RexRobot.rearRightSwerveWheel.setDrive(output);
	    	}*/
    	}
    	counter++;
    }

    protected boolean isFinished() {
    	if(counter != 0) {
	    	double delta = angle - RexRobot.navx.getYaw();
	    	boolean answer = (Math.abs(RexRobot.oi.copilotController.getMainY()) > 0.1) || (RexRobot.oi.driveController.getLeftMagnitude() > 0.1) || (RexRobot.oi.driveController.getRightMagnitude() > 0.1) || (Math.abs(delta) < 0.10);
	    	SmartDashboard.putBoolean("Is Aligned", answer);
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
    	this.counter = 0;
    }

    protected void interrupted() {
    	this.counter = 0;
    }
    
}