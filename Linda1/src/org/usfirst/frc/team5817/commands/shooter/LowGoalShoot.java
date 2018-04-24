package org.usfirst.frc.team5817.commands.shooter;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class LowGoalShoot extends Command {
	
    public LowGoalShoot() {
        requires(RexRobot.shooterRotation);
        requires(RexRobot.shooter);
        setRunWhenDisabled(false);
        setInterruptible(true);
    }

    protected void initialize() {
    	RexRobot.shooterRotation.setUnderVerticalPositionMode();
    }

    protected void execute() {
    	RexRobot.shooterRotation.setRaw(220);
    	RexRobot.shooter.setLeft(0.7);
    	RexRobot.shooter.setRight(-0.7);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {
    	end();
    }

}