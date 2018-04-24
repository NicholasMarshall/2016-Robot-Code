package org.usfirst.frc.team5817.commands.drive;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class ZeroGyro extends Command {

    public ZeroGyro() {
        setInterruptible(true);
        this.setRunWhenDisabled(true);
    }

    protected void initialize() {}

    protected void execute() {
    	RexRobot.navx.zeroYaw();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {
    	end();
    }
    
}