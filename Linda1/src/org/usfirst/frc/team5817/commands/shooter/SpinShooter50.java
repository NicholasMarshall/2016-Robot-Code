package org.usfirst.frc.team5817.commands.shooter;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class SpinShooter50 extends Command {

    public SpinShooter50() {
        requires(RexRobot.shooter);
        setRunWhenDisabled(false);
        setInterruptible(true);
    }

    protected void initialize() {
    	RexRobot.shooter.stop();
    }

    protected void execute() {
    	RexRobot.shooter.setLeft(1.0);
    	RexRobot.shooter.setRight(-1.0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	RexRobot.shooter.stop();
    }

    protected void interrupted() {
    	end();
    }

}