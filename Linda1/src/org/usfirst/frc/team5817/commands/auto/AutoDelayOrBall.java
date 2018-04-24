package org.usfirst.frc.team5817.commands.auto;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoDelayOrBall extends Command {

    public AutoDelayOrBall(double length) {
        setTimeout(length);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return isTimedOut() || !RexRobot.intake.isBallLoaded();
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	
    }
    
}