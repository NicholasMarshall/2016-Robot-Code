package org.usfirst.frc.team5817.commands.shooter;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class StopConveyor extends Command {

    public StopConveyor() {
        requires(RexRobot.conveyor);
        setInterruptible(true);
        setRunWhenDisabled(false);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	RexRobot.conveyor.stop();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	RexRobot.conveyor.stop();
    }

    protected void interrupted() {
    	end();
    }
    
}