package org.usfirst.frc.team5817.commands.auto;

import edu.wpi.first.wpilibj.command.Command;

public class AutoDelay extends Command {

    public AutoDelay(double length) {
        setTimeout(length);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	
    }
    
}