package org.usfirst.frc.team5817.commands.intake;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class StopIntake extends Command {

    public StopIntake() {
        requires(RexRobot.intake);
        setInterruptible(true);
        setRunWhenDisabled(false);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	RexRobot.intake.stop();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	RexRobot.intake.stop();
    }

    protected void interrupted() {
    	end();
    }
    
}