package org.usfirst.frc.team5817.commands.shooter;

import org.usfirst.frc.team5817.main.RexRobot;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopShooter extends Command {

    public TeleopShooter() {
        requires(RexRobot.shooter);
        setRunWhenDisabled(false);
        setInterruptible(true);
    }

    protected void initialize() {
    	RexRobot.shooter.stop();
    }

    protected void execute() {
    	RexRobot.shooter.setLeft(RexRobot.oi.copilotController.getSliderAxis());
    	RexRobot.shooter.setRight(-RexRobot.oi.copilotController.getSliderAxis());
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