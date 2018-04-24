package org.usfirst.frc.team5817.commands.intake;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeDown extends Command {

    public IntakeDown() {
        requires(RexRobot.intakeRotation);
        setInterruptible(true);
        setRunWhenDisabled(false);
    }

    protected void initialize() {
    	RexRobot.intakeRotation.setPercentMode();
    }

    protected void execute() {
    	RexRobot.intakeRotation.set(0.7);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	RexRobot.intakeRotation.set(0.0);
    }

    protected void interrupted() {
    	end();
    }
    
}