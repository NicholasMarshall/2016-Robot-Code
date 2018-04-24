package org.usfirst.frc.team5817.commands.shooter;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class ConveyorOut extends Command {

    public ConveyorOut() {
        requires(RexRobot.conveyor);
        setInterruptible(true);
        setRunWhenDisabled(false);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	RexRobot.conveyor.set(-1.0);
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