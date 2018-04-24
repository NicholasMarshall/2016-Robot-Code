package org.usfirst.frc.team5817.commands.shooter;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class SetShooterAngleAboveVertical extends Command {
	
	private double value;
	
    public SetShooterAngleAboveVertical(double value) {
        requires(RexRobot.shooterRotation);
        setRunWhenDisabled(false);
        setInterruptible(true);
        this.value = value;
    }

    protected void initialize() {
    	RexRobot.shooterRotation.setAboveVerticalPositionMode();
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