package org.usfirst.frc.team5817.commands.drive;

import org.usfirst.frc.team5817.main.RexRobot;
import org.usfirst.frc.team5817.main.RobotMap;
import org.usfirst.frc.team5817.util.RexMath;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopDrive extends Command {

	long lastDriveTime, lastStopTime = 0;
	double batteryMultiplier = 1.0;
	
    public TeleopDrive() {
        requires(RexRobot.frontLeftSwerveWheel);
        requires(RexRobot.frontRightSwerveWheel);
        requires(RexRobot.rearLeftSwerveWheel);
        requires(RexRobot.rearRightSwerveWheel);
        setInterruptible(true);
        this.setRunWhenDisabled(false);
    }

    protected void initialize() {
    	RexRobot.frontLeftSwerveWheel.setPositionSteerMode();
    	RexRobot.frontRightSwerveWheel.setPositionSteerMode();
    	RexRobot.rearLeftSwerveWheel.setPositionSteerMode();
    	RexRobot.rearRightSwerveWheel.setPositionSteerMode();
    	
    	RexRobot.frontLeftSwerveWheel.setSpeedDriveMode();
    	RexRobot.frontRightSwerveWheel.setSpeedDriveMode();
    	RexRobot.rearLeftSwerveWheel.setSpeedDriveMode();
    	RexRobot.rearRightSwerveWheel.setSpeedDriveMode();
    	
    	RexRobot.frontLeftSwerveWheel.zeroDrive();
    	RexRobot.frontRightSwerveWheel.zeroDrive();
    	RexRobot.rearLeftSwerveWheel.zeroDrive();
    	RexRobot.rearRightSwerveWheel.zeroDrive();
    }

    protected void execute() {
    	if(RexRobot.oi.driveController.getLeftMagnitude() > 0.1 && RexRobot.oi.driveController.getRightMagnitude() > 0.1) {
    		double omega = RexRobot.oi.driveController.getRightX() * 500.0 * (RexRobot.oi.driveController.getLeftMagnitude());
    		double tangentialVelocity = RobotMap.DRIVE_BASE_RADIUS_FEET * omega;
    		double frontLeftTangentialAngle = 45.0;
    		double frontRightTangentialAngle = 135.0;
    		double rearRightTangentialAngle = 215.0;
    		double rearLeftTangentialAngle = 205.0;
    		
    		double forwardVelocity = RexRobot.oi.driveController.getLeftMagnitude() * RexRobot.oi.driveController.getLeftMagnitude() * 600.0 * batteryMultiplier;
    		double forwardAngle = RexRobot.oi.driveController.getLeftDirection();
    		
    		double[] frontLeftOutput = RexMath.addPolarVectors(forwardVelocity, forwardAngle, tangentialVelocity, frontLeftTangentialAngle);
    		double[] frontRightOutput = RexMath.addPolarVectors(forwardVelocity, forwardAngle, tangentialVelocity, frontRightTangentialAngle);
    		double[] rearLeftOutput = RexMath.addPolarVectors(forwardVelocity, forwardAngle, tangentialVelocity, rearLeftTangentialAngle);
    		double[] rearRightOutput = RexMath.addPolarVectors(forwardVelocity, forwardAngle, tangentialVelocity, rearRightTangentialAngle);
    		
    		if(System.currentTimeMillis() - lastStopTime > 300) {
	    		RexRobot.frontLeftSwerveWheel.setDrive(frontLeftOutput[0]);
	    		RexRobot.frontRightSwerveWheel.setDrive(frontRightOutput[0]);
	    		RexRobot.rearLeftSwerveWheel.setDrive(rearLeftOutput[0]);
	    		RexRobot.rearRightSwerveWheel.setDrive(rearRightOutput[0]);
    		}
    		RexRobot.frontLeftSwerveWheel.setSteerDegrees(frontLeftOutput[1]);
    		RexRobot.frontRightSwerveWheel.setSteerDegrees(frontRightOutput[1]);
    		RexRobot.rearLeftSwerveWheel.setSteerDegrees(rearLeftOutput[1]);
    		RexRobot.rearRightSwerveWheel.setSteerDegrees(rearRightOutput[1]);
    		
    		lastDriveTime = System.currentTimeMillis();
    	} else if(RexRobot.oi.driveController.getLeftMagnitude() > 0.1) {
	    	RexRobot.frontLeftSwerveWheel.setSteerDegrees(RexRobot.oi.driveController.getLeftDirection());
	    	RexRobot.frontRightSwerveWheel.setSteerDegrees(RexRobot.oi.driveController.getLeftDirection());
	    	RexRobot.rearLeftSwerveWheel.setSteerDegrees(RexRobot.oi.driveController.getLeftDirection());
	    	RexRobot.rearRightSwerveWheel.setSteerDegrees(RexRobot.oi.driveController.getLeftDirection());
	    	if(System.currentTimeMillis() - lastStopTime > 300) {
		    	RexRobot.frontLeftSwerveWheel.setDrive(RexRobot.oi.driveController.getLeftMagnitude() * RexRobot.oi.driveController.getLeftMagnitude() * 600.0 * batteryMultiplier);
		    	RexRobot.frontRightSwerveWheel.setDrive(RexRobot.oi.driveController.getLeftMagnitude() * RexRobot.oi.driveController.getLeftMagnitude() * 600.0 * batteryMultiplier);
		    	RexRobot.rearLeftSwerveWheel.setDrive(RexRobot.oi.driveController.getLeftMagnitude() * RexRobot.oi.driveController.getLeftMagnitude() * 600.0 * batteryMultiplier);
		    	RexRobot.rearRightSwerveWheel.setDrive(RexRobot.oi.driveController.getLeftMagnitude() * RexRobot.oi.driveController.getLeftMagnitude() * 600.0 * batteryMultiplier);
	    	}
	    	lastDriveTime = System.currentTimeMillis();
    	} else if(RexRobot.oi.driveController.getRightMagnitude() > 0.1) {
    		RexRobot.frontLeftSwerveWheel.setSteerDegrees(45.0);
	    	RexRobot.frontRightSwerveWheel.setSteerDegrees(135.0);
	    	RexRobot.rearLeftSwerveWheel.setSteerDegrees(305.0);
	    	RexRobot.rearRightSwerveWheel.setSteerDegrees(215.0);
	    	RexRobot.frontLeftSwerveWheel.setDrive(RexRobot.oi.driveController.getRightX() * RexRobot.oi.driveController.getRightX() * Math.signum(RexRobot.oi.driveController.getRightX()) * 600.0 * batteryMultiplier);
	    	RexRobot.frontRightSwerveWheel.setDrive(RexRobot.oi.driveController.getRightX() * RexRobot.oi.driveController.getRightX() * Math.signum(RexRobot.oi.driveController.getRightX()) * 600.0 * batteryMultiplier);
	    	RexRobot.rearLeftSwerveWheel.setDrive(RexRobot.oi.driveController.getRightX() * RexRobot.oi.driveController.getRightX() * Math.signum(RexRobot.oi.driveController.getRightX()) * 600.0 * batteryMultiplier);
	    	RexRobot.rearRightSwerveWheel.setDrive(RexRobot.oi.driveController.getRightX() * RexRobot.oi.driveController.getRightX() * Math.signum(RexRobot.oi.driveController.getRightX()) * 600.0 * batteryMultiplier);
    	} else {
    		if(System.currentTimeMillis() - lastDriveTime > 300) {
    			RexRobot.frontLeftSwerveWheel.setSteerDegrees(45.0);
    	    	RexRobot.frontRightSwerveWheel.setSteerDegrees(135.0);
    	    	RexRobot.rearLeftSwerveWheel.setSteerDegrees(305.0);
    	    	RexRobot.rearRightSwerveWheel.setSteerDegrees(215.0);
    	    	lastStopTime  = System.currentTimeMillis();
    		}
    		RexRobot.frontLeftSwerveWheel.setPercentDriveMode();
        	RexRobot.frontRightSwerveWheel.setPercentDriveMode();
        	RexRobot.rearLeftSwerveWheel.setPercentDriveMode();
        	RexRobot.rearRightSwerveWheel.setPercentDriveMode();
    		RexRobot.frontLeftSwerveWheel.setDrive(0.0);
	    	RexRobot.frontRightSwerveWheel.setDrive(0.0);
	    	RexRobot.rearLeftSwerveWheel.setDrive(0.0);
	    	RexRobot.rearRightSwerveWheel.setDrive(0.0);
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {
    	end();
    }
    
}