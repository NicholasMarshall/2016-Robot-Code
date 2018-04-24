package org.usfirst.frc.team5817.commands.shooter;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class SetShooterAngleUnderVertical extends Command {
	
	private double value;
	
    public SetShooterAngleUnderVertical(double value) {
        requires(RexRobot.shooterRotation);
        setRunWhenDisabled(false);
        setInterruptible(false);
        this.value = value;
    }

    protected void initialize() {
    	RexRobot.shooterRotation.setUnderVerticalPositionMode();
    }

    protected void execute() {
    	RexRobot.shooterRotation.setRaw(value);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {
    	end();
    }

}