package org.usfirst.frc.team5817.commands.auto;

import org.usfirst.frc.team5817.commands.drive.FollowSpeedProfile;
import org.usfirst.frc.team5817.commands.drive.OrientWheels;
import org.usfirst.frc.team5817.commands.drive.StopDrive;
import org.usfirst.frc.team5817.commands.drive.ZeroGyro;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ReachFromNeutral extends CommandGroup {
    
    public  ReachFromNeutral() {
    	addParallel(new OrientWheels(0.0));
    	addSequential(new ZeroGyro());
    	addSequential(new AutoDelay(1.0));
    	addSequential(new FollowSpeedProfile(1.25, 400, 0.5, 400, 0.75, true));
        addSequential(new StopDrive());
    }
    
}