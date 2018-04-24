package org.usfirst.frc.team5817.commands.intake;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class Outtake extends Command {

    public Outtake() {
        requires(RexRobot.intake);
        setInterruptible(true);
        setRunWhenDisabled(false);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	RexRobot.intake.setHorizontal(-1.0);
    	RexRobot.intake.setVertical(-1.0);
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