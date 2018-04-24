package org.usfirst.frc.team5817.commands.shooter;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class ConveyorIn extends Command {

    public ConveyorIn() {
        requires(RexRobot.conveyor);
        setInterruptible(true);
        setRunWhenDisabled(false);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	if(RexRobot.intake.isBallLoaded()) {
    		RexRobot.conveyor.set(1.0);
    	} else {
    		RexRobot.conveyor.stop();
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	RexRobot.conveyor.stop();
    }

    protected void interrupted() {
    	end();
    }
    
}