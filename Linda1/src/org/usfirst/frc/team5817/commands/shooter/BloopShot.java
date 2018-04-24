package org.usfirst.frc.team5817.commands.shooter;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class BloopShot extends Command {
	
    public BloopShot() {
        requires(RexRobot.shooterRotation);
        requires(RexRobot.shooter);
        setRunWhenDisabled(false);
        setInterruptible(true);
    }

    protected void initialize() {
    	RexRobot.shooterRotation.setUnderVerticalPositionMode();
    }

    protected void execute() {
    	RexRobot.shooterRotation.setRaw(400);
    	RexRobot.shooter.setLeft(0.4);
    	RexRobot.shooter.setRight(-0.4);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {
    	end();
    }

}