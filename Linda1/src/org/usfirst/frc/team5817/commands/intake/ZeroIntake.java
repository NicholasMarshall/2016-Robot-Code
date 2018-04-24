package org.usfirst.frc.team5817.commands.intake;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class ZeroIntake extends Command {

    public ZeroIntake() {
        requires(RexRobot.intakeRotation);
        setInterruptible(true);
        this.setRunWhenDisabled(true);
    }

    protected void initialize() {}

    protected void execute() {
    	RexRobot.intakeRotation.zeroIntake();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {
    	end();
    }
    
}