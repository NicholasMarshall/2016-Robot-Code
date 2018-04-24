package org.usfirst.frc.team5817.commands.drive;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class ZeroSteer extends Command {

    public ZeroSteer() {
        requires(RexRobot.frontLeftSwerveWheel);
        requires(RexRobot.frontRightSwerveWheel);
        requires(RexRobot.rearLeftSwerveWheel);
        requires(RexRobot.rearRightSwerveWheel);
        setInterruptible(true);
        this.setRunWhenDisabled(true);
    }

    protected void initialize() {}

    protected void execute() {
    	RexRobot.frontLeftSwerveWheel.zeroSteer();
    	RexRobot.frontRightSwerveWheel.zeroSteer();
    	RexRobot.rearLeftSwerveWheel.zeroSteer();
    	RexRobot.rearRightSwerveWheel.zeroSteer();
    	System.out.println("Zeroed!");
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {
    	end();
    }
    
}