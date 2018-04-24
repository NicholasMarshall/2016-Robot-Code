package org.usfirst.frc.team5817.commands.intake;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeGoToPosition extends Command {

	private double position;
	
    public IntakeGoToPosition(double position) {
        requires(RexRobot.intakeRotation);
        setInterruptible(true);
        setRunWhenDisabled(false);
        this.position = position;
    }

    protected void initialize() {
    	RexRobot.intakeRotation.setPositionMode();
    }

    protected void execute() {
    	RexRobot.intakeRotation.set(position);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {
    	end();
    }
    
}