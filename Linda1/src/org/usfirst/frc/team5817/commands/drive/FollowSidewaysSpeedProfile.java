package org.usfirst.frc.team5817.commands.drive;

import org.usfirst.frc.team5817.main.RexRobot;
import org.usfirst.frc.team5817.util.TrapezoidalSpeedProfile;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class FollowSidewaysSpeedProfile extends Command {

	TrapezoidalSpeedProfile profile;
	Timer timer;
	double multiple = 1.0;
	boolean curve = false;
	
    public FollowSidewaysSpeedProfile(double length, double value2, double time2, double value3, double time3, boolean curve) {
    	requires(RexRobot.frontLeftSwerveWheel);
        requires(RexRobot.frontRightSwerveWheel);
        requires(RexRobot.rearLeftSwerveWheel);
        requires(RexRobot.rearRightSwerveWheel);
        setInterruptible(false);
        setRunWhenDisabled(false);
        setTimeout(length);
        
        profile = new TrapezoidalSpeedProfile(0.0, 0.0, length);
        profile.setPoint2(value2, time2);
        profile.setPoint3(value3, time3);
        
        this.curve = curve;
        
        timer = new Timer();
    }
    
    public FollowSidewaysSpeedProfile(double length, double value2, double time2, double value3, double time3, boolean curve, boolean reverse) {
    	requires(RexRobot.frontLeftSwerveWheel);
        requires(RexRobot.frontRightSwerveWheel);
        requires(RexRobot.rearLeftSwerveWheel);
        requires(RexRobot.rearRightSwerveWheel);
        setInterruptible(true);
        setRunWhenDisabled(false);
        setTimeout(length);
        
        profile = new TrapezoidalSpeedProfile(0.0, 0.0, length);
        profile.setPoint2(value2, time2);
        profile.setPoint3(value3, time3);
        
        timer = new Timer();
        
        if(reverse) {
        	multiple = -1.0;
        } else {
        	multiple = 1.0;
        }
        
        this.curve = curve;
    }

    protected void initialize() {
    	RexRobot.frontLeftSwerveWheel.setSpeedDriveMode();
    	RexRobot.frontRightSwerveWheel.setSpeedDriveMode();
    	RexRobot.rearLeftSwerveWheel.setSpeedDriveMode();
    	RexRobot.rearRightSwerveWheel.setSpeedDriveMode();
    	timer.start();
    }

    protected void execute() {
    	if(curve) {
    		double angle = RexRobot.navx.getYaw();
    		double Kp = (0.2 * (profile.getValue(timer.get()) * multiple));
    		double add = Kp * angle;
	    	RexRobot.frontLeftSwerveWheel.setDrive((profile.getValue(timer.get())) * multiple);
		   	RexRobot.frontRightSwerveWheel.setDrive((profile.getValue(timer.get())) * multiple);
		   	RexRobot.rearLeftSwerveWheel.setDrive((profile.getValue(timer.get()) - add) * multiple);
		   	RexRobot.rearRightSwerveWheel.setDrive((profile.getValue(timer.get()) - add) * multiple);
    	} else {
    		RexRobot.frontLeftSwerveWheel.setDrive((profile.getValue(timer.get())) * multiple);
		   	RexRobot.frontRightSwerveWheel.setDrive((profile.getValue(timer.get())) * multiple);
		   	RexRobot.rearLeftSwerveWheel.setDrive((profile.getValue(timer.get())) * multiple);
		   	RexRobot.rearRightSwerveWheel.setDrive((profile.getValue(timer.get())) * multiple);
    	}
    }

    protected boolean isFinished() {
    	return isTimedOut() || (RexRobot.oi.driveController.getLeftMagnitude() > 0.1) || (RexRobot.oi.driveController.getRightMagnitude() > 0.1);
    }

    protected void end() {
    	RexRobot.frontLeftSwerveWheel.setDrive(0.0);
    	RexRobot.frontRightSwerveWheel.setDrive(0.0);
    	RexRobot.rearLeftSwerveWheel.setDrive(0.0);
    	RexRobot.rearRightSwerveWheel.setDrive(0.0);
    }

    protected void interrupted() {
    	
    }
}
