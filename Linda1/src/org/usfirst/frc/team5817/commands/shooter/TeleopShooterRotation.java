package org.usfirst.frc.team5817.commands.shooter;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopShooterRotation extends Command {

	public int lastFrame = 0;
	
    public TeleopShooterRotation() {
        requires(RexRobot.shooterRotation);
        setRunWhenDisabled(false);
        setInterruptible(true);
    }

    protected void initialize() {
    	RexRobot.shooterRotation.setPercentMode();
    	lastFrame = 0;
    }

    protected void execute() {
    	if(Math.abs(RexRobot.oi.copilotController.getMainY()) > 0.10) {
    		if(lastFrame == 1) {
    			RexRobot.shooterRotation.setPercentMode();
    		}
    		RexRobot.shooterRotation.setRaw(-RexRobot.oi.copilotController.getMainY() * 0.333333);
    		lastFrame = 0;
    	} else {
    		if(lastFrame == 0) {
    			RexRobot.shooterRotation.setUnderVerticalPositionMode();
    			RexRobot.shooterRotation.setRaw(RexRobot.shooterRotation.getRaw());
    		}
    		lastFrame = 1;
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