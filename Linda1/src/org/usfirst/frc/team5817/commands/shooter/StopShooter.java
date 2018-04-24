package org.usfirst.frc.team5817.commands.shooter;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class StopShooter extends Command {

    public StopShooter() {
        requires(RexRobot.shooter);
        setRunWhenDisabled(false);
        setInterruptible(true);
    }

    protected void initialize() {
    	RexRobot.shooter.stop();
    }

    protected void execute() {
    	RexRobot.shooter.setLeft(0.0);
    	RexRobot.shooter.setRight(0.0);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	RexRobot.shooter.stop();
    }

    protected void interrupted() {
    	end();
    }

}