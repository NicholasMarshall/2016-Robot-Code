package org.usfirst.frc.team5817.commands.auto;

import org.usfirst.frc.team5817.commands.drive.FollowSpeedProfile;
import org.usfirst.frc.team5817.commands.drive.OrientWheels;
import org.usfirst.frc.team5817.commands.drive.ZeroGyro;
import org.usfirst.frc.team5817.commands.intake.IntakeGoToPosition;
import org.usfirst.frc.team5817.main.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossLowBarFromNeutralZone extends CommandGroup {
    
    public CrossLowBarFromNeutralZone() {
    	addParallel(new OrientWheels(0.0));
    	addSequential(new ZeroGyro());
    	addSequential(new AutoDelay(2.0));
    	addParallel(new IntakeGoToPosition(RobotMap.INTAKE_LOW_POSITION));
        addSequential(new FollowSpeedProfile(6.0, 400, 1.0, 400, 5.0, true));
        addParallel(new IntakeGoToPosition(RobotMap.INTAKE_STORAGE_POSITION));
        addSequential(new AutoDelay(1.0));
    }
    
}