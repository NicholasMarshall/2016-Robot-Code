package org.usfirst.frc.team5817.commands.shooter;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class FireShooter extends Command {

    public FireShooter() {
        requires(RexRobot.conveyor);
        setRunWhenDisabled(false);
        setInterruptible(false);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	RexRobot.conveyor.set(1.0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	RexRobot.conveyor.stop();
    }

    protected void interrupted() {
    	end();
    }

}