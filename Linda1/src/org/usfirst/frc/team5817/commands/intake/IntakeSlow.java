package org.usfirst.frc.team5817.commands.intake;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeSlow extends Command {

    public IntakeSlow() {
        requires(RexRobot.intake);
        setInterruptible(true);
        setRunWhenDisabled(false);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	if(RexRobot.intake.isBallLoaded()) {
	    	RexRobot.intake.setHorizontal(1.0);
	    	RexRobot.intake.setVertical(0.4);
    	} else {
    		RexRobot.intake.stop();
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	RexRobot.intake.stop();
    }

    protected void interrupted() {
    	end();
    }
    
}