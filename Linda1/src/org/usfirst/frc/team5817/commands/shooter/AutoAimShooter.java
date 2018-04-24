package org.usfirst.frc.team5817.commands.shooter;

import org.usfirst.frc.team5817.main.RexRobot;
import org.usfirst.frc.team5817.util.TrapezoidalSpeedProfile;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoAimShooter extends Command {

	TrapezoidalSpeedProfile profile;
	Timer timer;
	double isNegative = 1.0;
	double shotPosition = 200.0;
	double centerX, centerY = 0.0;
	
    public AutoAimShooter() {
        requires(RexRobot.shooterRotation);
        setInterruptible(false);
        setRunWhenDisabled(false);
        timer = new Timer();
    }

    protected void initialize() {
        RexRobot.shooterRotation.setUnderVerticalPositionMode();
        centerX = RexRobot.getBestCenterX();
        centerY = RexRobot.getBestCenterY();
        double distance = (0.0000006 * Math.pow(centerY, 3)) + (0.00008 * Math.pow(centerY, 2)) + (0.0092 * centerY) + 3.9479; //distance = 6E-07x3 + 8E-05x2 + 0.0092x + 3.9479
        shotPosition = -(0.0355 * Math.pow(distance, 3)) + (1.6658 * Math.pow(distance, 2)) - (30.572 * distance) + 477.99; //position = -0.0355x3 + 1.6658x2 - 30.572x + 512.99hh
    }

    protected void execute() {
    	if(centerY != 0.0) {
    		RexRobot.shooterRotation.setRaw(shotPosition);
    	}
    }

    protected boolean isFinished() {
    	return (Math.abs(RexRobot.oi.copilotController.getMainY()) > 0.3) || (Math.abs(RexRobot.shooterRotation.getRaw() - shotPosition) < 5.0) || (RexRobot.oi.driveController.getLeftMagnitude() > 0.1) || (RexRobot.oi.driveController.getRightMagnitude() > 0.1);
    }

    protected void end() {
    	new TeleopShooterRotation().start();
    }

    protected void interrupted() {
    	end();
    }
}
